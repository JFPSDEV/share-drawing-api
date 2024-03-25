package com.jfteam.sharedrawing.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentRequestDto {
    private Long parentCommentId;
    private Long profileId;
    private Long drawingId;
    private String message;
}
