package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.like.ShortLikeDrawingDto;
import com.jfteam.sharedrawing.model.LikeDrawing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawingDetailsResponseDto extends DrawingItemResponseDto {
    private List<CommentDto> comments;
    private List<ShortLikeDrawingDto> likes;
}
