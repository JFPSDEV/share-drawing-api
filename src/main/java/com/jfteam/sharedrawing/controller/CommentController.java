package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.comment.AddCommentRequestDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentRequestDto;
import com.jfteam.sharedrawing.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController extends MainController {

    protected final String COMMENT_PATH = "/comment";

    protected final CommentService service;

    @PostMapping(AUTH_PATH + COMMENT_PATH)
    public ResponseEntity<CommentDto> comment(@RequestBody AddCommentRequestDto addCommentRequestDto) {
        return service.comment(addCommentRequestDto);
    }

    @PostMapping(AUTH_PATH + COMMENT_PATH + "/reply")
    public ResponseEntity<CommentDto> reply(@RequestBody ReplyCommentRequestDto replyComRequest) {
        return service.reply(replyComRequest);
    }
}
