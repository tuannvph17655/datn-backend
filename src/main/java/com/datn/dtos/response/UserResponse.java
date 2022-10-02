package com.datn.dtos.response;


import com.datn.entity.User;
import com.datn.utils.common.CopyUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponse {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String role;

    private boolean active;

    public static UserResponse from(User src) {
        UserResponse target = UserResponse.builder().build();
        CopyUtils.copy(src,target,"role");
        return target.setRole(src.getRole().toString());
    }


}
