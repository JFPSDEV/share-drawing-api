package com.jfteam.sharedrawing.service;

public interface IAuthenticationService {

    public String register(String firstName, String lastName, String email, String pwd, String pseudo);
    public String authenticate(String email, String pwd);
}
