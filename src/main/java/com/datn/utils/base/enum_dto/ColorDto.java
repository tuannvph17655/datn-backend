package com.datn.utils.base.enum_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColorDto {

    private String id;

    @NotBlank(message = "Không được để trống tên màu sắc")
    private String name;
    @NotBlank(message = "Không được để trống mã màu")

    private String hex;

}
