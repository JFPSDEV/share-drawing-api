package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.AuthenticationRequestDto;
import com.jfteam.sharedrawing.dto.AuthenticationResponseDto;
import com.jfteam.sharedrawing.dto.RegisterRequestDto;

public interface IAuthenticationService {

    public AuthenticationResponseDto register(RegisterRequestDto payload);
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
