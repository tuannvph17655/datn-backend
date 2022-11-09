package com.datn.dto.admin.color;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorRes {
    private String id;
    private String name;
    private String hex;
    private Boolean active;
}
