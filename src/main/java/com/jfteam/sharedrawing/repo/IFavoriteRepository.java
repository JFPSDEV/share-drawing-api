package com.jfteam.sharedrawing.repo;

import com.jfteam.sharedrawing.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {

}
