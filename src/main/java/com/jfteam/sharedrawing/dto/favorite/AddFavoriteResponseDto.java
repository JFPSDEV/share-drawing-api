package com.jfteam.sharedrawing.dto.favorite;

import com.jfteam.sharedrawing.dto.drawing.DrawingDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddFavoriteResponseDto {
    private long id;
    private ShortProfileResponseDto profile;
    private DrawingDto drawing;
}
