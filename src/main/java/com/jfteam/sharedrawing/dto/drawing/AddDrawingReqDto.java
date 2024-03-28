package com.jfteam.sharedrawing.dto.drawing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddDrawingReqDto {
    DrawingDto drawing;
    List<Long> tagIds;
}
