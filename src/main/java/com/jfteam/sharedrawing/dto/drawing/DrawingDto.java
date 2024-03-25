package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawingDto {
    private long id;
    private String name;
    private String description;
    private List<String> sourceUrls;
    private List<TagItemResponseDto> tags;
}
