package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.drawing.*;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResponseDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.dto.like.UnlikeRequestDto;
import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingRequestDto;
import org.springframework.security.core.Authentication;
import com.jfteam.sharedrawing.model.*;

import java.util.List;
import java.util.Optional;

public interface IDrawingService {
    public LikeDrawingResponseDto getDrawingLike(Long profileId, Long entityId);
    public DrawingDetailsResponseDto getDrawingDetails(Drawing drawing);

    public  LikeDrawingResponseDto likeDrawing(LikeRequestDto payload);

    public Boolean unlikeDrawing(UnlikeRequestDto payload);

    public RatingResponseDto rateDrawing(UpsertRatingRequestDto payload);

    public DrawingByIdResponseDto getById(Long id, Authentication auth);

    public DrawingItemResponseDto create(AddDrawingRequestDto payload);

    public List<DrawingItemResponseDto> getList(Optional<FilterDrawingRequestDto> payload);
}
