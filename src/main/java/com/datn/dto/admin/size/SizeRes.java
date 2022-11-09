package com.datn.dto.admin.size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SizeRes {
    private String id;
    private String name;
    private String code;
    private Boolean status;

}
