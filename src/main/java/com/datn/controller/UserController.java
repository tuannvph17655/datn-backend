package com.datn.controller;

import com.datn.dtos.request.UserRequest;
import com.datn.dtos.response.UserResponse;
import com.datn.entity.User;
import com.datn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<UserResponse>> findAllUser(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<UserResponse> userResponses = userService.findAllUser(page,size);
        return new ResponseEntity<>(userResponses,HttpStatus.OK);
    }

    @PostMapping("/add-new-user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
