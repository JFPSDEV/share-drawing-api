package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.favorite.AddFavoriteReqDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResDto;
import com.jfteam.sharedrawing.dto.favorite.DeleteFavoriteReqDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IFavoriteController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Add a drawing to favorite")
    public ResponseEntity<AddFavoriteResDto> add(@RequestBody AddFavoriteReqDto req, Authentication auth);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Delete a favorite drawing")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth);
}
