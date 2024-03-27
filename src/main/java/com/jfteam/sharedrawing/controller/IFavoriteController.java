package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.favorite.AddFavoriteRequestDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResponseDto;
import com.jfteam.sharedrawing.dto.favorite.DeleteFavoriteRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IFavoriteController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Add a drawing to favorite")
    public ResponseEntity<AddFavoriteResponseDto> add(@RequestBody AddFavoriteRequestDto addFavoriteRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Delete a favorite drawing")
    public ResponseEntity<Void> delete(@RequestBody DeleteFavoriteRequestDto payload);
}
