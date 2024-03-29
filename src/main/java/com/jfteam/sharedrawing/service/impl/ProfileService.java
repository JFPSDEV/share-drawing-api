package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.exception.NoSuchEntityException;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.repo.IProfileRepository;
import com.jfteam.sharedrawing.repo.IUserRepository;
import com.jfteam.sharedrawing.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileService extends GetableService<Profile> implements IProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
    private final IProfileRepository profileRepo;
    private final IUserRepository userRepo;

    public Profile getByEmail(String userEmail) {
        try {
            var user = userRepo.findByEmail(userEmail);
            return user.get().getProfile();
        }
        catch(NoSuchElementException e) {
            throw new NoSuchEntityException("User not found with email: " + userEmail, e);
        }
    }

    public Profile getByAuth(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            return getByEmail(userEmail);
        }
        return null;
    }
}
