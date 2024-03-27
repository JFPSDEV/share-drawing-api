package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.controller.ICommentController;
import com.jfteam.sharedrawing.dto.comment.AddCommentRequestDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeCommentResponseDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.dto.like.UnlikeRequestDto;
import com.jfteam.sharedrawing.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController  extends MainController implements ICommentController {

    protected final String COMMENT_PATH = "/comment";

    protected final CommentService service;

    @PostMapping(AUTH_PATH + COMMENT_PATH)
    public ResponseEntity<CommentDto> comment(@RequestBody AddCommentRequestDto addCommentRequestDto) {
        CommentDto response = service.comment(addCommentRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/reply")
    public ResponseEntity<CommentDto> reply(@RequestBody ReplyCommentRequestDto replyComRequest) {
        CommentDto response = service.reply(replyComRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/like")
    public ResponseEntity<LikeCommentResponseDto> like(@RequestBody LikeRequestDto likeRequestDto) {
        LikeCommentResponseDto response =  service.likeComment(likeRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(AUTH_PATH + COMMENT_PATH +  "/unlike")
    public ResponseEntity<Void> unlike(@RequestBody UnlikeRequestDto unlikeRequestDto) {
        boolean likeDeleted = service.unlikeComment(unlikeRequestDto);
        if(likeDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
