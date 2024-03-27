package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.LikeDrawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILikeDrawingRepository extends JpaRepository<LikeDrawing, Long> {
    @Query("SELECT l FROM LikeDrawing l WHERE l.profile.id = :profileId AND l.drawing.id = :drawingId")
    public LikeDrawing findByProfileIdAndDrawingId(@Param("profileId") Long profileId, @Param("drawingId")Long drawingId);
}
