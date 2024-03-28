package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.model.Rating;
import com.jfteam.sharedrawing.repo.IRatingRepository;
import com.jfteam.sharedrawing.service.IRaitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService extends GetableService<Rating> implements IRaitingService {

    final private IRatingRepository ratingRepo;


    public Rating findByProfileIdAndDrawingId(Long profileId, Long drawingId) {
        return  ratingRepo.findByProfileIdAndDrawingId(profileId, drawingId);

    }
}
