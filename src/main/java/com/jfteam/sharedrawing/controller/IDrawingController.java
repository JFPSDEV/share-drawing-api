package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.drawing.AddDrawingReqDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingByIdResDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingItemResDto;
import com.jfteam.sharedrawing.dto.drawing.FilterDrawingReqDto;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResDto;
import com.jfteam.sharedrawing.dto.like.LikeReqDto;
import com.jfteam.sharedrawing.dto.like.UnlikeReqDto;
import com.jfteam.sharedrawing.dto.rating.RatingResDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingReqDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.hibernate.query.SortDirection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IDrawingController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get with filter the drawing list")
    public ResponseEntity<List<DrawingItemResDto>> getDrawingList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) SortDirection sortDirection,
            @RequestParam(required = false) List<Long> tagIds
    );

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get one drawing details by id")
    public ResponseEntity<DrawingByIdResDto> getDrawingById(@PathVariable Long id, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Create a new drawing")
    public ResponseEntity<DrawingItemResDto> addDrawing(@RequestBody AddDrawingReqDto req, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Like a drawing")
    public ResponseEntity<LikeDrawingResDto> like(@RequestBody LikeReqDto likeRequestDto, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Unlike a drawing")
    public ResponseEntity<Void> unlike(@PathVariable Long id, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Rate a drawing")
    public ResponseEntity<RatingResDto> rate(@RequestBody UpsertRatingReqDto payload, Authentication auth);
}
