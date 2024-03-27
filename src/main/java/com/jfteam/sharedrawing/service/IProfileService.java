package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.model.Profile;
import org.springframework.security.core.Authentication;

public interface IProfileService {
    Profile getById(Long id);

    Profile getByEmail(String userEmail);

    Profile getByAuth(Authentication authentication);
}
