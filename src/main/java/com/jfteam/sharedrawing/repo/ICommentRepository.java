package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
}
