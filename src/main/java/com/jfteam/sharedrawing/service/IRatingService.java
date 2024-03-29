package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.model.Rating;

public interface IRatingService {
    public Rating findByProfileIdAndDrawingId(Long profileId, Long drawingId);
}
