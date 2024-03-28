package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.dto.drawing.*;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingReqDto;
import com.jfteam.sharedrawing.controller.IDrawingController;
import com.jfteam.sharedrawing.dto.like.LikeDrawingResDto;
import com.jfteam.sharedrawing.dto.rating.RatingResDto;
import com.jfteam.sharedrawing.dto.like.LikeReqDto;
import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.service.IDrawingService;
import com.jfteam.sharedrawing.service.IProfileService;
import com.jfteam.sharedrawing.service.IRaitingService;
import org.hibernate.query.SortDirection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DrawingController extends AbstractController implements IDrawingController {
    private final String DRAWING_PATH = "/drawing";
    private final IDrawingService drawingSrv;
    private final IProfileService profileSrv;
    private final IRaitingService raitingSrv;

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/add")
    public ResponseEntity<DrawingItemResDto> addDrawing(@RequestBody AddDrawingReqDto req, Authentication auth) {
        Drawing drawing = mapEntityToDto(req.getDrawing(), Drawing.class);
        return ResponseEntity.ok(
                mapEntityToDto(
                        drawingSrv.create(drawing, req.getTagIds(), auth),
                        DrawingItemResDto.class
                )
        );
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/like")
    public ResponseEntity<LikeDrawingResDto> like(@RequestBody LikeReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapEntityToDto(
                        drawingSrv.likeDrawing(req.getEntityId(), req.getLiked(), auth),
                        LikeDrawingResDto.class
                )
        );
    }

    @DeleteMapping(AUTH_PATH + DRAWING_PATH +  "/unlike/{id}")
    public ResponseEntity<Void> unlike(@PathVariable Long id, Authentication auth) {
        boolean likeDeleted = drawingSrv.unlikeDrawing(id, auth);
        if(likeDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(AUTH_PATH + DRAWING_PATH +  "/rate")
    public ResponseEntity<RatingResDto> rate(@RequestBody UpsertRatingReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapEntityToDto(
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
                        .map(drawing -> mapEntityToDto(drawing, DrawingItemResDto.class))
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
            ratingRes = mapEntityToDto(raitingSrv.findByProfileIdAndDrawingId(profile.getId(), id), RatingResDto.class);
            likeDrawingRes = mapEntityToDto(drawingSrv.getDrawingLike(profile.getId(), id), LikeDrawingResDto.class);
        }

        return ResponseEntity.ok(
                DrawingByIdResDto
                        .builder()
                        .drawing(mapEntityToDto(drawing, DrawingDetailsResDto.class))
                        .rating(mapEntityToDto(ratingRes, RatingResDto.class))
                        .likeDrawing(mapEntityToDto(likeDrawingRes, LikeDrawingResDto.class))
                        .build()
        );
    }
}
