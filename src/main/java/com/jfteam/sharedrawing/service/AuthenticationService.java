package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.AuthenticationRequest;
import com.jfteam.sharedrawing.dto.AuthenticationResponse;
import com.jfteam.sharedrawing.dto.RegisterRequest;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.repo.ProfileRepository;
import com.jfteam.sharedrawing.repo.UserRepository;
import com.jfteam.sharedrawing.model.User;
import com.jfteam.sharedrawing.model.Role;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder pwdEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ProfileRepository profileRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(pwdEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        Profile profile = request.getProfile();
        if (profile != null) {
            profile.setUser(user);
            profileRepository.save(profile);
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
