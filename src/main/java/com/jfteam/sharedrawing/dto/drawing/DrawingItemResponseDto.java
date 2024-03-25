package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.profile.ShortProfileDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawingItemResponseDto extends DrawingDto {
    private ShortProfileDto profile;
}
