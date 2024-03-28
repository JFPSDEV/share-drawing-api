package com.jfteam.sharedrawing.dto.like;

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
public class LikeDrawingResDto {
    private long id;
    private ShortDrawingResDto drawing;
    private ShortProfileResDto profile;
    private Boolean liked;
}
