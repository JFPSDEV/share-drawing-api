package com.jfteam.sharedrawing.dto.drawing;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResDto;
import com.jfteam.sharedrawing.dto.rating.RatingResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawingByIdResDto {
    DrawingDetailsResDto drawing;
    RatingResDto rating;
    LikeDrawingResDto likeDrawing;
}
