package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.drawing.AddDrawingRequestDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingByIdResponseDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingItemResponseDto;
import com.jfteam.sharedrawing.dto.drawing.FilterDrawingRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResponseDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.dto.like.UnlikeRequestDto;
import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IDrawingController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get with filter the drawing list")
    public ResponseEntity<List<DrawingItemResponseDto>> getDrawingList(Optional<FilterDrawingRequestDto> filterDtoOpt);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get one drawing details by id")
    public ResponseEntity<DrawingByIdResponseDto> getDrawingById(@PathVariable Long id, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Create a new drawing")
    public ResponseEntity<DrawingItemResponseDto> addDrawing(@RequestBody AddDrawingRequestDto addDrawingRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Like a drawing")
    public ResponseEntity<LikeDrawingResponseDto> like(@RequestBody LikeRequestDto likeRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Unlike a drawing")
    public ResponseEntity<Void> unlike(@RequestBody UnlikeRequestDto unlikeRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Rate a drawing")
    public ResponseEntity<RatingResponseDto> rate(@RequestBody UpsertRatingRequestDto payload);
}
