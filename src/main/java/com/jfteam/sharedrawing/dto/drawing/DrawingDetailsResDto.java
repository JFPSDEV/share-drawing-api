package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawingDetailsResDto extends DrawingItemResDto {
    private List<CommentDto> comments;
    //private List<ShortLikeResponseDto> likes;
    private int countLikes;
    private int countDislikes;
    private double rateAverage;
    private int countRates;
}
