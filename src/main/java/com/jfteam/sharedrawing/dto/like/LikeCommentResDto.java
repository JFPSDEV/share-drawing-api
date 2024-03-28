package com.jfteam.sharedrawing.dto.like;

import com.jfteam.sharedrawing.dto.comment.ShortCommentDto;
import com.jfteam.sharedrawing.dto.profile.ShortProfileResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeCommentResDto {
    private long id;
    private ShortCommentDto comment;
    private ShortProfileResDto profile;
    private Boolean liked;
}
