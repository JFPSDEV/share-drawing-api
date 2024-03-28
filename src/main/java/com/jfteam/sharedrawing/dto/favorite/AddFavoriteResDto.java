package com.jfteam.sharedrawing.dto.favorite;

import com.jfteam.sharedrawing.dto.drawing.DrawingDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddFavoriteResDto {
    private long id;
    private ShortProfileResDto profile;
    private DrawingDto drawing;
}
