package com.jfteam.sharedrawing.dto.like;

import com.jfteam.sharedrawing.dto.profile.ShortProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLikeResponseDto {
    private ShortProfileResponseDto profile;
    private Boolean liked;
}
