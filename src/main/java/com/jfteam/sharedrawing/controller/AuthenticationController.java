package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.config.AppConstants;
import com.jfteam.sharedrawing.dto.AuthReqDto;
import com.jfteam.sharedrawing.dto.AuthResDto;
import com.jfteam.sharedrawing.dto.RegisterReqDto;
import com.jfteam.sharedrawing.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.PUBLIC_PATH + "/auth")
@RequiredArgsConstructor
public class AuthenticationController extends AbstractController {
    private final IAuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResDto> register(@RequestBody RegisterReqDto req) {
       return ResponseEntity.ok(
               AuthResDto.builder().token(
                       service.register(
                           req.getFirstName(),
                           req.getLastName(),
                           req.getEmail(),
                           req.getPassword(),
                           req.getPseudo()
                       )
               ).build()
       );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResDto> authenticate(@RequestBody AuthReqDto req) {
        return ResponseEntity.ok(
                AuthResDto.builder().token(
                        service.authenticate(
                                req.getEmail(),
                                req.getPassword()
                        )
                ).build()
        );
    }
}
