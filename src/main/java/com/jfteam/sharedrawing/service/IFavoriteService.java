package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.model.Favorite;
import org.springframework.security.core.Authentication;

public interface IFavoriteService {
    public Favorite getById(Long id);
    public Favorite create(Long drawingId, Authentication auth);
    public Boolean delete(Long favoriteId, Authentication auth);
}
