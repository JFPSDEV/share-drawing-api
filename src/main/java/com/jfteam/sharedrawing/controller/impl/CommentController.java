package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.controller.ICommentController;
import com.jfteam.sharedrawing.dto.comment.AddCommentReqDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentReqDto;
import com.jfteam.sharedrawing.dto.like.LikeCommentResDto;
import com.jfteam.sharedrawing.dto.like.LikeReqDto;
import com.jfteam.sharedrawing.dto.like.UnlikeReqDto;
import com.jfteam.sharedrawing.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController extends AbstractController implements ICommentController {
    protected final String COMMENT_PATH = "/comment";

    protected final ICommentService service;

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/send")
    public ResponseEntity<CommentDto> comment(@RequestBody AddCommentReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapEntityToDto(
                        service.comment(req.getDrawingId(), req.getMessage(), auth),
                        CommentDto.class
                )
        );
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/reply")
    public ResponseEntity<CommentDto> reply(@RequestBody ReplyCommentReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapEntityToDto(
                        service.reply(req.getParentCommentId(), req.getMessage(), auth),
                        CommentDto.class
                )
        );
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/like")
    public ResponseEntity<LikeCommentResDto> like(@RequestBody LikeReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapEntityToDto(
                        service.likeComment(req.getEntityId(), req.getLiked(), auth),
                        LikeCommentResDto.class
                )
        );
    }

    @DeleteMapping(AUTH_PATH + COMMENT_PATH +  "/unlike/{id}")
    public ResponseEntity<Void> unlike(@PathVariable Long id, Authentication auth) {
        boolean likeDeleted = service.unlikeComment(id, auth);
        if(likeDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
