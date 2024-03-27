package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import com.jfteam.sharedrawing.model.Rating;
import com.jfteam.sharedrawing.repo.IRatingRepository;
import com.jfteam.sharedrawing.service.IRaitingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService extends GetableService<Rating> implements IRaitingService {

    final private IRatingRepository ratingRepo;

    public RatingResponseDto getRatingDetails(Rating rating) {
        return  new ModelMapper().map(rating, RatingResponseDto.class);
    }

    public RatingResponseDto findByProfileIdAndDrawingId(Long profileId, Long drawingId) {
        Rating rating = ratingRepo.findByProfileIdAndDrawingId(profileId, drawingId);

        if(rating != null) {
            return getRatingDetails(rating);
        }
        return null;
    }
}
