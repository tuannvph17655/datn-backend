package com.datn.dto.admin.detail;

import com.datn.utils.constants.enums.RoleEnum;
import com.datn.utils.constants.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private String fullName;
}
