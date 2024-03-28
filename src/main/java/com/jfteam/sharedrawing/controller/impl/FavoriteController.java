package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.config.AppConstants;
import com.jfteam.sharedrawing.controller.IFavoriteController;
import com.jfteam.sharedrawing.dto.drawing.DrawingByIdResDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteReqDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResDto;
import com.jfteam.sharedrawing.dto.favorite.DeleteFavoriteReqDto;
import com.jfteam.sharedrawing.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.AUTH_PATH + "/favorite")
public class FavoriteController extends AbstractController implements IFavoriteController {

    private final IFavoriteService service;
    @PostMapping("/add")
    public ResponseEntity<AddFavoriteResDto> add(@RequestBody AddFavoriteReqDto req, Authentication auth) {
        return ResponseEntity.ok(
                mapEntityToDto(
                        service.create(req.getDrawingId(), auth),
                        AddFavoriteResDto.class
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
        boolean favoriteDeleted = service.delete(id, auth);
        if(favoriteDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
