package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepository extends JpaRepository<Profile, Long> {
}
