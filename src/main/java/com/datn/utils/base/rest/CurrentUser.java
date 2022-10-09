package com.datn.utils.base.rest;

import com.datn.utils.constants.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {
    private String id;
    private String combinationName;
    private RoleEnum role;
    private String email;
}
