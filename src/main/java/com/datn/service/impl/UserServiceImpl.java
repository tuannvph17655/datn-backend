package com.datn.service.impl;

import com.datn.dto.customer.user.RegisterDto;
import com.datn.service.UserService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.validator.user.RegisterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PuddyRepository repository;

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        var userDto = repository.userRepository.findUserDtoByEmail(value, Boolean.TRUE);
        if (userDto == null) {
            throw new UsernameNotFoundException(String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.USER_VI.toLowerCase(Locale.ROOT)));
        }

        return User.builder()
                .username(userDto.getEmail())
                .password(userDto.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(userDto.getRole().name())))
                .build();
    }

    /**
     * Khách hàng tự đăng ký tài khoản cho mình
     */
    @Override
    public Object registerCustomer(RegisterDto body) {
        RegisterValidator.validateRegisterDto(body);
        return null;
    }

    @Override
    public Object getCurrentUserProfile() {
        var repository = BeanUtils.getBean(PuddyRepository.class);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var id = authentication.getPrincipal().toString().replace("\"", "").trim();
        if (StringUtils.isNullOrEmpty(id)) {
            throw new PuddyException(PuddyCode.USER_NOT_FOUND);
        }

        var customer = repository.userRepository.findCustomerById(id);
        if (customer == null) {
            throw new PuddyException(PuddyCode.MUST_LOGIN);
        }
        return customer;

    }


}
