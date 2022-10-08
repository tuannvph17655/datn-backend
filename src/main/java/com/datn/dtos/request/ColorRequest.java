package com.datn.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorRequest {
    @NotBlank(message = "Không được để trống tên Color")
    private String name;
    @NotBlank(message = "Không được để trống tên Hex")
    private String hex;
}
