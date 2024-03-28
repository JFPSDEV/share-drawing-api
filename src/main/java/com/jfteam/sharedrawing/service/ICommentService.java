package com.jfteam.sharedrawing.service;
import com.jfteam.sharedrawing.model.Comment;
import com.jfteam.sharedrawing.model.LikeComment;
import org.springframework.security.core.Authentication;


public interface ICommentService {

    public LikeComment likeComment(Long commentId, Boolean liked, Authentication auth);

    public Boolean unlikeComment(Long commentId, Authentication auth);

    public Comment comment(Long drawingId, String message, Authentication auth);

    public Comment reply(Long parentCommentId, String message, Authentication auth);
}
