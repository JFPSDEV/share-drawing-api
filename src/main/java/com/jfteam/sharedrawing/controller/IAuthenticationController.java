package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.AuthReqDto;
import com.jfteam.sharedrawing.dto.AuthResDto;
import com.jfteam.sharedrawing.dto.RegisterReqDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Register new user account")
    public ResponseEntity<AuthResDto> register(@RequestBody RegisterReqDto request);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Authenticate current user account")
    public ResponseEntity<AuthResDto> authenticate(@RequestBody AuthReqDto request);
}
