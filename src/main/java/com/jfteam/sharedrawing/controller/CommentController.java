package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.comment.AddCommentReqDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentReqDto;
import com.jfteam.sharedrawing.dto.like.LikeCommentResDto;
import com.jfteam.sharedrawing.dto.like.LikeReqDto;
import com.jfteam.sharedrawing.model.Comment;
import com.jfteam.sharedrawing.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CommentController extends AbstractController  {
    protected final String COMMENT_PATH = "/comment";

    protected final ICommentService service;

    @GetMapping(PUBLIC_PATH + COMMENT_PATH +  "/{id}")
    public ResponseEntity<Comment> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/send")
    public ResponseEntity<CommentDto> comment(@RequestBody AddCommentReqDto req, Authentication auth) {
        CommentDto response = mapModel(
                service.comment(req.getDrawingId(), req.getMessage(), auth),
                CommentDto.class
        );
        return ResponseEntity.created(URI.create(PUBLIC_PATH + COMMENT_PATH +"/" + response.getId()))
                .body(response);
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/reply")
    public ResponseEntity<CommentDto> reply(@RequestBody ReplyCommentReqDto req, Authentication auth) {
        CommentDto response = mapModel(
                service.reply(req.getParentCommentId(), req.getMessage(), auth),
                CommentDto.class
        );
        return ResponseEntity.created(URI.create(PUBLIC_PATH + COMMENT_PATH +"/" + response.getId()))
                .body(response);
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/like")
    public ResponseEntity<LikeCommentResDto> like(@RequestBody LikeReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapModel(
                        service.likeComment(req.getEntityId(), req.getLiked(), auth),
                        LikeCommentResDto.class
                )
        );
    }

    @DeleteMapping(AUTH_PATH + COMMENT_PATH +  "/unlike/{id}")
    public ResponseEntity<Void> unlike(@PathVariable Long id, Authentication auth) {
        service.unlikeComment(id, auth);
        return ResponseEntity.noContent().build();
    }
}
