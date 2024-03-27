package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.AuthenticationRequestDto;
import com.jfteam.sharedrawing.dto.AuthenticationResponseDto;
import com.jfteam.sharedrawing.dto.RegisterRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationController {
    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Register new user account")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Authenticate current user account")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request);
}
