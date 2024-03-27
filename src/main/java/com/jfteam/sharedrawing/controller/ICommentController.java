package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.comment.AddCommentRequestDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeCommentResponseDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.dto.like.UnlikeRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICommentController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Post a new comment")
    public ResponseEntity<CommentDto> comment(@RequestBody AddCommentRequestDto addCommentRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Reply to a comment")
    public ResponseEntity<CommentDto> reply(@RequestBody ReplyCommentRequestDto replyComRequest);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Like a comment")
    public ResponseEntity<LikeCommentResponseDto> like(@RequestBody LikeRequestDto likeRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Unlike a comment")
    public ResponseEntity<Void> unlike(@RequestBody UnlikeRequestDto unlikeRequestDto);
}