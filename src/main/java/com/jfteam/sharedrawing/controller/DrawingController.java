package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.drawing.AddDrawingRequestDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingDetailsResponseDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingItemResponseDto;
import com.jfteam.sharedrawing.dto.drawing.FilterDrawingRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeDrawingRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResponseDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.service.DrawingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DrawingController extends MainController {
    private final String DRAWING_PATH = "/drawing";
    private final DrawingService service;

    @GetMapping(PUBLIC_PATH + DRAWING_PATH + "/list")
    public ResponseEntity<List<DrawingItemResponseDto>> getDrawingList(Optional<FilterDrawingRequestDto> filterDtoOpt) {
        return service.getList(filterDtoOpt);
    }

    @GetMapping(PUBLIC_PATH + DRAWING_PATH +"/{id}")
    public ResponseEntity<DrawingDetailsResponseDto> getDrawingById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/add")
    public ResponseEntity<DrawingItemResponseDto> addDrawing(@RequestBody AddDrawingRequestDto addDrawingRequestDto) {
        return service.create(addDrawingRequestDto);
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/like")
    public ResponseEntity<DrawingDetailsResponseDto> addDrawing(@RequestBody LikeRequestDto likeRequestDto) {
        return service.likeDrawing(likeRequestDto);
    }


}
