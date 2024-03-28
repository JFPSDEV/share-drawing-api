package com.jfteam.sharedrawing.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentReqDto {
    private Long parentCommentId;
    private String message;
}
