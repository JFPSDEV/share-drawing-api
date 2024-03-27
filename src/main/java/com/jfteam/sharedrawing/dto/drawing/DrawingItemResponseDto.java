package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.profile.ShortProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawingItemResponseDto extends DrawingDto {
    private ShortProfileResponseDto profile;
}
