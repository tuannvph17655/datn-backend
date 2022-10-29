package com.datn.dto.admin.order.change_status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusDto {
    private String id;
    @NotBlank(message = "Không được để trống status !")
    private String status;
    private String note;
}
