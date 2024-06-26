package com.jfteam.sharedrawing.dto.comment;

import com.jfteam.sharedrawing.dto.profile.ShortProfileResDto;
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
    private ShortProfileResDto profile;
    private List<CommentDto> replies;
  //  private List<ShortLikeResponseDto> likes;
    private int countLikes;
    private int countDislikes;
}
