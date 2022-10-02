package com.datn.service;

import com.datn.dtos.request.UserRequest;
import com.datn.dtos.response.UserResponse;
import com.datn.entity.User;
import com.datn.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<UserResponse> findAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(UserResponse::from).collect(Collectors.toList());
        Page<UserResponse> pages = new PageImpl<UserResponse>(userResponses, pageable, userResponses.size());
        return pages;
    }

    public User createUser(UserRequest userRequest) {
        User user = User.copy(userRequest);
        user.setActive(true);
        return userRepository.save(user);
    }
}
