package com.jfteam.sharedrawing.dto.rating;

import com.jfteam.sharedrawing.dto.drawing.ShortDrawingResDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingResDto {
    private long id;
    private ShortProfileResDto profile;
    private ShortDrawingResDto drawing;
    private Integer rate;
}
