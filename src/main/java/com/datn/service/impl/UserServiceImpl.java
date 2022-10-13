package com.datn.service.impl;

import com.datn.dto.customer.user.UserDto;
import com.datn.service.UserService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.constants.PuddyConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PuddyRepository repository;

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        UserDto userDto = repository.userRepository.findUserDtoByEmail(value, Boolean.TRUE);
        if (userDto == null) {
            throw new UsernameNotFoundException(String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.USER_VI.toLowerCase(Locale.ROOT)));
        }

        return User.builder()
                .username(userDto.getEmail())
                .password(userDto.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(userDto.getRole().name())))
                .build();
    }
}
