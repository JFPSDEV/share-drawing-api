package com.jfteam.sharedrawing.dto.like;

import com.jfteam.sharedrawing.dto.profile.ShortProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLikeDrawingDto {
    private ShortProfileDto profile;
    private boolean liked;
}
