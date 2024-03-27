package com.jfteam.sharedrawing.dto.like;

import com.jfteam.sharedrawing.dto.comment.ShortCommentDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeCommentResponseDto {
    private long id;
    private ShortCommentDto comment;
    private ShortProfileResponseDto profile;
    private Boolean liked;
}
