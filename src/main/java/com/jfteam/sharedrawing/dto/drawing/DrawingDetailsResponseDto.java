package com.jfteam.sharedrawing.dto.drawing;

import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.like.ShortLikeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawingDetailsResponseDto extends DrawingItemResponseDto {
    private List<CommentDto> comments;
    //private List<ShortLikeResponseDto> likes;
    private int countLikes;
    private int countDislikes;
    private double rateAverage;
    private int countRates;
}
