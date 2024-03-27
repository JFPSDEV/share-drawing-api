package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.comment.AddCommentRequestDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.comment.ReplyCommentRequestDto;
import com.jfteam.sharedrawing.dto.like.*;
import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.*;
import com.jfteam.sharedrawing.repo.ICommentRepository;
import com.jfteam.sharedrawing.repo.ILikeCommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends LikeService<Comment, LikeComment> {
    private final ICommentRepository commentRepo;
    private final ProfileService profileSrv;
    private final DrawingService drawingSrv;
    private final ILikeCommentRepository likeCommentRepo;

    protected CommentService(ProfileService profileSrv, ICommentRepository commentRepo, ProfileService profileSrv1, DrawingService drawingSrv, ILikeCommentRepository likeCommentRepo) {
        super(profileSrv);
        this.commentRepo = commentRepo;
        this.profileSrv = profileSrv1;
        this.drawingSrv = drawingSrv;
        this.likeCommentRepo = likeCommentRepo;
    }

    @Override
    public JpaRepository<LikeComment, Long> getLikeRepo() {
        return likeCommentRepo;
    }

    @Override
    protected LikeComment createLikeEntity(Profile profile, Boolean liked, Comment comment) {
            LikeComment newLike = new LikeComment();
            newLike.setProfile(profile);
            newLike.setComment(comment);
            newLike.setLiked(liked);
            return newLike;
    }

    @Override
    protected LikeComment getLikeByProfileIdAndEntityId(Long profileId, Long entityId) {
        return likeCommentRepo.findByProfileIdAndCommentId(profileId, entityId);
    }

    public CommentDto getCommentDetails(Comment comment) {
        return new ModelMapper().map(comment, CommentDto.class);
    }

    public LikeCommentResponseDto likeComment(LikeRequestDto payload) {
        return new ModelMapper().map(like(payload), LikeCommentResponseDto.class);
    }

    public Boolean unlikeComment(UnlikeRequestDto payload) {
        return unlike(payload);
    }

    public CommentDto comment(AddCommentRequestDto payload) {
        Profile profile = profileSrv.getById(payload.getProfileId());
        Drawing drawing = drawingSrv.getById(payload.getDrawingId());

        try {
            Comment newComment = Comment.builder()
                    .message(payload.getMessage())
                    .profile(profile)
                    .drawing(drawing)
                    .build();

            Comment savedComment = commentRepo.save(newComment);
            return getCommentDetails(savedComment);
        } catch (Exception e) {
            throw new ServerSideException("Error creating Comment entity", e);
        }
    }


    public CommentDto reply(ReplyCommentRequestDto payload) {
        Comment parentComment = getById(payload.getParentCommentId());
        Profile profile = profileSrv.getById(payload.getProfileId());

        try {
            Comment reply = new Comment();
            reply.setProfile(profile);
            reply.setParentComment(parentComment);
            reply.setMessage(payload.getMessage());

            Comment savedComment = commentRepo.save(commentRepo.save(reply));
            return getCommentDetails(savedComment);
        } catch (Exception e) {
            throw new ServerSideException("Error creating Reply Comment entity", e);
        }
    }

}
