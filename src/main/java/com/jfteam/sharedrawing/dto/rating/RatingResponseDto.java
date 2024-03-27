package com.jfteam.sharedrawing.dto.rating;

import com.jfteam.sharedrawing.dto.drawing.ShortDrawingResponseDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {
    private long id;
    private ShortProfileResponseDto profile;
    private ShortDrawingResponseDto drawing;
    private Integer rate;
}
