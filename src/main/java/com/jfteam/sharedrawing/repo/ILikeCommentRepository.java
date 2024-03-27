package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
    @Query("SELECT l FROM LikeComment l WHERE l.profile.id = :profileId AND l.comment.id = :commentId")
    public LikeComment findByProfileIdAndCommentId(@Param("profileId") Long profileId, @Param("commentId")Long commentId);
}
