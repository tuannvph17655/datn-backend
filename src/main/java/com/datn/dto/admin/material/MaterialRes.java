package com.datn.dto.admin.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MaterialRes {
    private String id;
    private String name;
    private Boolean active;
    private String code;
}
