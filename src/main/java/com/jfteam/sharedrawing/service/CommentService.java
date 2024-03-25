package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.comment.AddCommentRequestDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentRequestDto;
import com.jfteam.sharedrawing.model.Comment;
import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.repo.CommentRepository;
import com.jfteam.sharedrawing.repo.DrawingRepository;
import com.jfteam.sharedrawing.repo.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepo;
    private final ProfileRepository profileRepo;
    private final DrawingRepository drawingRepo;

    public ResponseEntity<CommentDto> comment(AddCommentRequestDto addCommentRequestDto) {
        Profile profile = profileRepo.findById(addCommentRequestDto.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + addCommentRequestDto.getProfileId()));

        Drawing drawing = drawingRepo.findById(addCommentRequestDto.getDrawingId())
                .orElseThrow(() -> new IllegalArgumentException("Drawing not found with id: " + addCommentRequestDto.getDrawingId()));

        Comment newComment = Comment.builder()
                .message(addCommentRequestDto.getMessage())
                .profile(profile)
                .drawing(drawing)
                .build();

        Comment savedComment = commentRepo.save(newComment);

        CommentDto commentDto = new ModelMapper().map(savedComment, CommentDto.class);

        return ResponseEntity.ok(commentDto);
    }


    public ResponseEntity<CommentDto> reply(ReplyCommentRequestDto replyComRequest) {
        Comment parentComment = commentRepo.findById(replyComRequest.getParentCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + replyComRequest.getParentCommentId()));

        Profile profile = profileRepo.findById(replyComRequest.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + replyComRequest.getProfileId()));


        Comment reply = new Comment();
        reply.setProfile(profile);
        reply.setParentComment(parentComment);
        reply.setMessage(replyComRequest.getMessage());

        CommentDto commentDto = new ModelMapper().map(commentRepo.save(reply), CommentDto.class);

        return ResponseEntity.ok(commentDto);
    }

}
