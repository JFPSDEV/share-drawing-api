package com.jfteam.sharedrawing.dto.drawing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortDrawingResponseDto {
    private long id;
    private String name;
}
