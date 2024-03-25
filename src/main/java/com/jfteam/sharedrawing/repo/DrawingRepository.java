package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DrawingRepository extends JpaRepository<Drawing, Long>, JpaSpecificationExecutor<Drawing> {

}
