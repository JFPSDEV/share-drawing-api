package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingRequestDto;
import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.Rating;
import org.modelmapper.ModelMapper;

public interface IRaitingService {
    public RatingResponseDto getRatingDetails(Rating rating);

    public RatingResponseDto findByProfileIdAndDrawingId(Long profileId, Long drawingId);
}
