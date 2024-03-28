package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.*;
import com.jfteam.sharedrawing.repo.ICommentRepository;
import com.jfteam.sharedrawing.repo.ILikeCommentRepository;
import com.jfteam.sharedrawing.service.ICommentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends LikeService<Comment, LikeComment> implements ICommentService {
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

    public LikeComment likeComment(Long commentId, Boolean liked, Authentication auth) {
        return likeOrDislike(commentId, liked, auth);
    }

    public Boolean unlikeComment(Long commentId, Authentication auth) {
        return unlike(commentId, auth);
    }

    public Comment comment(Long drawingId, String message, Authentication auth) {
        Profile profile = profileSrv.getByAuth(auth);
        Drawing drawing = drawingSrv.getById(drawingId);

        try {
            return commentRepo.save(
                    Comment.builder()
                    .message(message)
                    .profile(profile)
                    .drawing(drawing)
                    .build()
            );
        } catch (Exception e) {
            throw new ServerSideException("Error creating Comment entity", e);
        }
    }


    public Comment reply(Long parentCommentId, String message, Authentication auth) {
        Comment parentComment = getById(parentCommentId);
        Profile profile = profileSrv.getByAuth(auth);
        try {
            Comment reply = new Comment();
            reply.setProfile(profile);
            reply.setParentComment(parentComment);
            reply.setMessage(message);
            return commentRepo.save(commentRepo.save(reply));
        } catch (Exception e) {
            throw new ServerSideException("Error creating Reply Comment entity", e);
        }
    }

}
