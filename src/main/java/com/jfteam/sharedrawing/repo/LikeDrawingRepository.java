package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.LikeDrawing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDrawingRepository extends JpaRepository<LikeDrawing, Long> {
}
