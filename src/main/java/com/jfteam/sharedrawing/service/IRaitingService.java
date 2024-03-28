package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.model.Rating;

public interface IRaitingService {
    public Rating findByProfileIdAndDrawingId(Long profileId, Long drawingId);
}
