package com.datn.dto.admin.detail;

import com.datn.utils.constants.enums.RoleEnum;
import com.datn.utils.constants.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private StatusEnum status;
    private Date createdDate;
    private RoleEnum role;
    private String fullName;
}
