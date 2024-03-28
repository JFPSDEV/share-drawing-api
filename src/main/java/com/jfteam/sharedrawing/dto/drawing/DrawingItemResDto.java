package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.profile.ShortProfileResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawingItemResDto extends DrawingDto {
    private ShortProfileResDto profile;
}
