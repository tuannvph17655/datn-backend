package com.datn.dtos.response;

import com.datn.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeResponse {
    private Long id;
    private String name;
    private String code;
    private boolean active;

    public static SizeResponse from(Size size){
        SizeResponse sizeResponse = SizeResponse
                .builder()
                .id(size.getId())
                .name(size.getName())
                .code(size.getCode())
                .active(size.isActive())
                .build();
        return sizeResponse;
    }
}
