package com.datn.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.datn.repository.UserRepository;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.constants.WsConst;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var email = request.getParameter(WsConst.UserFields.EMAIL_VAR);
        var password = request.getParameter(WsConst.UserFields.PASSWORD);
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        final var user = (User) authResult.getPrincipal();
        final var repository = BeanUtils.getBean(UserRepository.class);
        final var userEntity = repository.findByEmailAndActive(user.getUsername(), true);
        final var algorithm = Algorithm.HMAC256(WsConst.Values.JWT_SECRET.getBytes());
        final var accessToken = JWT.create()
                .withSubject(userEntity.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + WsConst.Values.ACCESS_TOKEN_EXPIRED))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(WsConst.UserFields.ROLE_VAR, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0))
                .withClaim(WsConst.UserFields.NAME_VAR, (Optional.of(userEntity.getUserName()).orElse("")).trim())
                .withClaim(WsConst.UserFields.EMAIL_VAR, userEntity.getEmail())
                .withClaim(WsConst.UserFields.ID_VAR, userEntity.getId())
                .sign(algorithm);

        final var refreshToken = JWT.create()
                .withSubject(Long.toString(userEntity.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + WsConst.Values.REFRESH_TOKEN_EXPIRED))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var responses = new HashMap<String, Object>();
        responses.put(WsConst.Nouns.ACCESS_TOKEN_FIELD, accessToken);
        responses.put(WsConst.Nouns.REFRESH_TOKEN_FIELD, refreshToken);

        new ObjectMapper().writeValue(response.getOutputStream(), responses);
    }
}
