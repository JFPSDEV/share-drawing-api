package com.jfteam.sharedrawing.dto.drawing;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResponseDto;
import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawingByIdResponseDto {
    DrawingDetailsResponseDto drawing;
    RatingResponseDto rating;
    LikeDrawingResponseDto likeDrawing;
}
