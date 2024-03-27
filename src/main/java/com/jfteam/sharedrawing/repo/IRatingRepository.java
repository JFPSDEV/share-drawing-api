package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT r FROM Rating r WHERE r.profile.id = :profileId AND r.drawing.id = :drawingId")
    public Rating findByProfileIdAndDrawingId(@Param("profileId") Long profileId, @Param("drawingId")Long drawingId);
}
