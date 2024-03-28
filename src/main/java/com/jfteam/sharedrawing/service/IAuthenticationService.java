package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.AuthReqDto;
import com.jfteam.sharedrawing.dto.AuthResDto;
import com.jfteam.sharedrawing.dto.RegisterReqDto;

public interface IAuthenticationService {

    public String register(String firstName, String lastName, String email, String pwd, String pseudo);
    public String authenticate(String email, String pwd);
}
