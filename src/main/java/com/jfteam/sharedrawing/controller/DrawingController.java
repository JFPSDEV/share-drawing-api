package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.drawing.AddDrawingReqDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingByIdResDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingDetailsResDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingItemResDto;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResDto;
import com.jfteam.sharedrawing.dto.like.LikeReqDto;
import com.jfteam.sharedrawing.dto.rating.RatingResDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingReqDto;
import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.service.IDrawingService;
import com.jfteam.sharedrawing.service.IProfileService;
import com.jfteam.sharedrawing.service.IRatingService;
import com.jfteam.sharedrawing.utils.SortDirection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DrawingController extends AbstractController {
    private final String DRAWING_PATH = "/drawing";
    private final IDrawingService drawingSrv;
    private final IProfileService profileSrv;
    private final IRatingService ratingSrv;

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/add")
    public ResponseEntity<DrawingItemResDto> addDrawing(@RequestBody AddDrawingReqDto req, Authentication auth) {
        Drawing drawing = mapModel(req.getDrawing(), Drawing.class);
        DrawingItemResDto response =  mapModel(
                drawingSrv.create(drawing, req.getTagIds(), auth),
                DrawingItemResDto.class
        );
        return ResponseEntity.created(URI.create(PUBLIC_PATH + DRAWING_PATH +"/" + response.getId()))
                .body(response);
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/like")
    public ResponseEntity<LikeDrawingResDto> like(@RequestBody LikeReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapModel(
                        drawingSrv.likeDrawing(req.getEntityId(), req.getLiked(), auth),
                        LikeDrawingResDto.class
                )
        );
    }

    @DeleteMapping(AUTH_PATH + DRAWING_PATH +  "/unlike/{id}")
    public ResponseEntity<Void> unlike(@PathVariable Long id, Authentication auth) {
        drawingSrv.unlikeDrawing(id, auth);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/rate")
    public ResponseEntity<RatingResDto> rate(@RequestBody UpsertRatingReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapModel(
                        drawingSrv.rateDrawing(req.getDrawingId(), req.getRate(), auth),
                        RatingResDto.class
                )
        );
    }

    @GetMapping(PUBLIC_PATH + DRAWING_PATH + "/list")
    public ResponseEntity<List<DrawingItemResDto>> getDrawingList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) SortDirection sortDirection,
            @RequestParam(required = false) List<Long> tagIds
    ) {
        return ResponseEntity.ok(
                drawingSrv.getList(page, size, sortDirection, tagIds)
                        .getContent()
                        .stream()
                        .map(drawing -> mapModel(drawing, DrawingItemResDto.class))
                        .toList()
        );
    }

    @GetMapping(PUBLIC_PATH + DRAWING_PATH +"/{id}")
    public ResponseEntity<DrawingByIdResDto> getDrawingById(@PathVariable Long id, Authentication auth) {
        Drawing drawing = drawingSrv.getById(id);
        Profile profile = profileSrv.getByAuth(auth);

        RatingResDto ratingRes = null;
        LikeDrawingResDto likeDrawingRes = null;

        if(profile != null) {
            ratingRes = mapModel(ratingSrv.findByProfileIdAndDrawingId(profile.getId(), id), RatingResDto.class);
            likeDrawingRes = mapModel(drawingSrv.getDrawingLike(profile.getId(), id), LikeDrawingResDto.class);
        }

        return ResponseEntity.ok(
                DrawingByIdResDto
                        .builder()
                        .drawing(mapModel(drawing, DrawingDetailsResDto.class))
                        .rating(mapModel(ratingRes, RatingResDto.class))
                        .likeDrawing(mapModel(likeDrawingRes, LikeDrawingResDto.class))
                        .build()
        );
    }
}
