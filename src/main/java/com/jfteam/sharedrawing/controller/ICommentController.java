package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.comment.AddCommentReqDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentReqDto;
import com.jfteam.sharedrawing.dto.like.LikeCommentResDto;
import com.jfteam.sharedrawing.dto.like.LikeReqDto;
import com.jfteam.sharedrawing.dto.like.UnlikeReqDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICommentController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Post a new comment")
    public ResponseEntity<CommentDto> comment(@RequestBody AddCommentReqDto req, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Reply to a comment")
    public ResponseEntity<CommentDto> reply(@RequestBody ReplyCommentReqDto replyComRequest, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Like a comment")
    public ResponseEntity<LikeCommentResDto> like(@RequestBody LikeReqDto likeReqDto, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Unlike a comment")
    public ResponseEntity<Void> unlike(@PathVariable Long id, Authentication auth);
}