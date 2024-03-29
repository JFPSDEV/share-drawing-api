package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.favorite.AddFavoriteReqDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResDto;
import com.jfteam.sharedrawing.model.Favorite;
import com.jfteam.sharedrawing.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class FavoriteController extends AbstractController {
    private final String FAVORITE_PATH = "/favorite";

    private final IFavoriteService service;

    @GetMapping(PUBLIC_PATH + FAVORITE_PATH +  "/{id}")
    public ResponseEntity<Favorite> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping(AUTH_PATH + FAVORITE_PATH +  "/add")
    public ResponseEntity<AddFavoriteResDto> add(@RequestBody AddFavoriteReqDto req, Authentication auth) {
        AddFavoriteResDto response = mapModel(
                service.create(req.getDrawingId(), auth),
                AddFavoriteResDto.class
        );

        return ResponseEntity.created(URI.create(PUBLIC_PATH + FAVORITE_PATH +"/" + response.getId()))
                .body(response);
    }

    @DeleteMapping(AUTH_PATH + FAVORITE_PATH + "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
        boolean favoriteDeleted = service.delete(id, auth);
        if(favoriteDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
