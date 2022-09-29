package com.datn.dtos.response;


import com.datn.entity.Role;
import com.datn.entity.User;
import com.datn.utils.common.CopyUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String role;
    private boolean active;

    public static UserResponse from(User src) {
        UserResponse target = UserResponse.builder().build();
        CopyUtils.copy(src,target);
        return target;
    }


}
