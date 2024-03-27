package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.AuthenticationRequestDto;
import com.jfteam.sharedrawing.dto.AuthenticationResponseDto;
import com.jfteam.sharedrawing.dto.RegisterRequestDto;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.repo.IProfileRepository;
import com.jfteam.sharedrawing.repo.IUserRepository;
import com.jfteam.sharedrawing.model.User;
import com.jfteam.sharedrawing.model.Role;
import com.jfteam.sharedrawing.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository IUserRepository;
    private final PasswordEncoder pwdEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IProfileRepository IProfileRepository;

    public AuthenticationResponseDto register(RegisterRequestDto payload) {
        User registerUser = IUserRepository.save(
               User
                .builder()
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .email(payload.getEmail())
                .password(pwdEncoder.encode(payload.getPassword()))
                .role(Role.USER)
                .build()
        );

        Profile profile  = IProfileRepository.save(
                Profile
                .builder()
                .pseudo(payload.getProfile().getPseudo())
                .user(registerUser)
                .build()
        );

        var jwtToken = jwtService.generateToken(profile.getUser());
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));

        var user = IUserRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }
}
