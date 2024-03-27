package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.config.AppConstants;
import com.jfteam.sharedrawing.controller.IAuthenticationController;
import com.jfteam.sharedrawing.dto.AuthenticationRequestDto;
import com.jfteam.sharedrawing.dto.AuthenticationResponseDto;
import com.jfteam.sharedrawing.dto.RegisterRequestDto;
import com.jfteam.sharedrawing.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.PUBLIC_PATH + "/auth")
@RequiredArgsConstructor
public class AuthenticationController implements IAuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
