package com.jfteam.sharedrawing.service.impl;


import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.Favorite;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.repo.IFavoriteRepository;
import com.jfteam.sharedrawing.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FavoriteService extends GetableService<Favorite> implements IFavoriteService {
    private final ProfileService profileSrv;
    private final DrawingService drawingSrv;
    private final IFavoriteRepository favoriteRepo;

    public Favorite create(Long drawingId, Authentication auth) {
        Profile profile = profileSrv.getByAuth(auth);
        Drawing drawing = drawingSrv.getById(drawingId);
        try {
            Favorite favorite = new Favorite();
            favorite.setProfile(profile);
            favorite.setDrawing(drawing);
            return favoriteRepo.save(favorite);
        } catch (Exception e) {
            throw new ServerSideException("Error create favorite", e);
        }
    }
    public Boolean delete(Long favoriteId, Authentication auth) {
        boolean deleted = false;
        Profile profile = profileSrv.getByAuth(auth);
        Favorite favorite = getById(favoriteId);
        try {
            if(Objects.equals(favorite.getProfile().getId(), profile.getId())) {
                favoriteRepo.delete(favorite);
                deleted = true;
            }
            return deleted;
        } catch (Exception e) {
            throw new ServerSideException("Error in deleting favorite", e);
        }
    }
}
