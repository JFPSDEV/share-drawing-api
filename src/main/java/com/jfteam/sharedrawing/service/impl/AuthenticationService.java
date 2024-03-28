package com.jfteam.sharedrawing.service.impl;

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

    public String register(String firstName, String lastName, String email, String pwd, String pseudo) {
        User registerUser = IUserRepository.save(
               User
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(pwdEncoder.encode(pwd))
                .role(Role.USER)
                .build()
        );

        Profile profile  = IProfileRepository.save(
                Profile
                .builder()
                .pseudo(pseudo)
                .user(registerUser)
                .build()
        );

        return jwtService.generateToken(profile.getUser());
    }

    public String authenticate(String email, String pwd) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
        var user = IUserRepository.findByEmail(email).orElseThrow();
        return jwtService.generateToken(user);
    }
}
