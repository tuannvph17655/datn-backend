package com.datn.dtos.request;

import com.datn.annotation.MustBeEnum;
import com.datn.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Tên người dùng không được để trống !")
    private String userName;
    @NotBlank(message = "Không được để trống password !")
    private String password;
    @NotBlank(message = "Không được để trống mail !")
    @Email(message = "Vui lòng nhập đúng định dạng email !")
    private String email;
    @MustBeEnum(value = Role.class,ignoreCase = false)
    private String role;
}
