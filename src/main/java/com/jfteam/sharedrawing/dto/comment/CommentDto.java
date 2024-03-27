package com.jfteam.sharedrawing.dto.comment;

import com.jfteam.sharedrawing.dto.like.ShortLikeResponseDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String message;
    private ShortProfileResponseDto profile;
    private List<CommentDto> replies;
  //  private List<ShortLikeResponseDto> likes;
    private int countLikes;
    private int countDislikes;
}
