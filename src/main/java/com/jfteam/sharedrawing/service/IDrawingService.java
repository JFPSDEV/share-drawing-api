package com.jfteam.sharedrawing.service;


import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.LikeDrawing;
import com.jfteam.sharedrawing.model.Rating;
import com.jfteam.sharedrawing.utils.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface IDrawingService extends IGetableService<Drawing> {

    public LikeDrawing getDrawingLike(Long profileId, Long entityId);

    public LikeDrawing likeDrawing(Long drawingId, Boolean liked, Authentication auth);

    public Boolean unlikeDrawing(Long drawingId, Authentication auth);

    public Rating rateDrawing(Long drawingId, Integer rate,  Authentication auth);

    public Drawing create(Drawing drawing, List<Long> tagsId, Authentication auth);

    public Page<Drawing> getList(Integer page, Integer size, SortDirection sortDirection, List<Long> tagIds);
}
