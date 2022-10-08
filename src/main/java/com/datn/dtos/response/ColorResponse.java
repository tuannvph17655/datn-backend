package com.datn.dtos.response;

import com.datn.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorResponse {
    private Long id;
    private String name;
    private String hex;
    private boolean active;

    public static ColorResponse from(Color color){
        ColorResponse colorResponse = ColorResponse
                .builder()
                .id(color.getId())
                .name(color.getName())
                .hex(color.getHex())
                .active(color.isActive())
                .build();
        return colorResponse;
    }
}
