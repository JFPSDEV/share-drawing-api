package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.favorite.AddFavoriteRequestDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResponseDto;
import com.jfteam.sharedrawing.dto.favorite.DeleteFavoriteRequestDto;
import com.jfteam.sharedrawing.model.Favorite;

public interface IFavoriteService {
    public Favorite getById(Long id);
    public AddFavoriteResponseDto create(AddFavoriteRequestDto payload);
    public Boolean delete(DeleteFavoriteRequestDto payload);
}
