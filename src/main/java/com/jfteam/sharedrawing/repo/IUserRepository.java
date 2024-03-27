package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
