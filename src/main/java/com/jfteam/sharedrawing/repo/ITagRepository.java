package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagRepository extends JpaRepository<Tag, Long> {

}
