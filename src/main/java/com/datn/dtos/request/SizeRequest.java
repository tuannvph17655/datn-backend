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
public class SizeRequest {
    @NotBlank(message = "Không được để trống tên size !")
    private String name;

    @NotBlank(message = "Không được để trống code size !")
    private String code;

//    @NotBlank(message = "Active đi !")
//    private boolean active;
}