package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Long> {

}
