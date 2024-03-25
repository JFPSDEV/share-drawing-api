package com.jfteam.sharedrawing.dto.comment;

import com.jfteam.sharedrawing.dto.profile.ShortProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortCommentDto {
    private long id;
    private String message;
    private ShortProfileDto profile;
}
