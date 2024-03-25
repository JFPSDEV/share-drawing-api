package com.jfteam.sharedrawing.dto.comment;

import com.jfteam.sharedrawing.dto.profile.ShortProfileDto;
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
    private ShortProfileDto profile;
    private List<CommentDto> replies;
}
