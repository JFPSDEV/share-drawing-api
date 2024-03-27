package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.controller.IDrawingController;
import com.jfteam.sharedrawing.dto.drawing.*;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResponseDto;
import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingRequestDto;
import com.jfteam.sharedrawing.dto.like.UnlikeRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.service.impl.DrawingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DrawingController extends MainController implements IDrawingController {


    private final String DRAWING_PATH = "/drawing";
    private final DrawingService service;

    @GetMapping(PUBLIC_PATH + DRAWING_PATH + "/list")
    public ResponseEntity<List<DrawingItemResponseDto>> getDrawingList(Optional<FilterDrawingRequestDto> filterDtoOpt) {
        List<DrawingItemResponseDto> response =  service.getList(filterDtoOpt);
        return ResponseEntity.ok(response);
    }

    @GetMapping(PUBLIC_PATH + DRAWING_PATH +"/{id}")
    public ResponseEntity<DrawingByIdResponseDto> getDrawingById(@PathVariable Long id, Authentication auth) {
        DrawingByIdResponseDto response = service.getById(id, auth);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/add")
    public ResponseEntity<DrawingItemResponseDto> addDrawing(@RequestBody AddDrawingRequestDto addDrawingRequestDto) {
        DrawingItemResponseDto response = service.create(addDrawingRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/like")
    public ResponseEntity<LikeDrawingResponseDto> like(@RequestBody LikeRequestDto likeRequestDto) {
        LikeDrawingResponseDto response = service.likeDrawing(likeRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(AUTH_PATH + DRAWING_PATH +  "/unlike")
    public ResponseEntity<Void> unlike(@RequestBody UnlikeRequestDto unlikeRequestDto) {
        boolean likeDeleted = service.unlikeDrawing(unlikeRequestDto);
        if(likeDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/rate")
    public ResponseEntity<RatingResponseDto> rate(@RequestBody UpsertRatingRequestDto payload) {
        RatingResponseDto response = service.rateDrawing(payload);
        return ResponseEntity.ok(response);
    }
}
