package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.config.AppConstants;
import com.jfteam.sharedrawing.controller.IFavoriteController;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteRequestDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResponseDto;
import com.jfteam.sharedrawing.dto.favorite.DeleteFavoriteRequestDto;
import com.jfteam.sharedrawing.service.impl.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.AUTH_PATH + "/favorite")
public class FavoriteController extends MainController implements IFavoriteController {

    private final FavoriteService service;
    @PostMapping("/add")
    public ResponseEntity<AddFavoriteResponseDto> add(@RequestBody AddFavoriteRequestDto addFavoriteRequestDto) {
        AddFavoriteResponseDto response = service.create(addFavoriteRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody DeleteFavoriteRequestDto payload) {
        boolean favoriteDeleted = service.delete(payload);
        if(favoriteDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
