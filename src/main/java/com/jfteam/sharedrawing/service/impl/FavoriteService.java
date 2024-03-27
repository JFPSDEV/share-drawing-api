package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.drawing.DrawingItemResponseDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteRequestDto;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResponseDto;
import com.jfteam.sharedrawing.dto.favorite.DeleteFavoriteRequestDto;
import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.Drawing;
import com.jfteam.sharedrawing.model.Favorite;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.model.Tag;
import com.jfteam.sharedrawing.repo.IFavoriteRepository;
import com.jfteam.sharedrawing.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService extends GetableService<Favorite> implements IFavoriteService {
    private final ProfileService profileSrv;
    private final DrawingService drawingSrv;
    private final IFavoriteRepository favoriteRepo;


    public AddFavoriteResponseDto create(AddFavoriteRequestDto payload) {
        Profile profile = profileSrv.getById(payload.getProfileId());
        Drawing drawing = drawingSrv.getById(payload.getDrawingId());

        try {
            Favorite favorite = new Favorite();
            favorite.setProfile(profile);
            favorite.setDrawing(drawing);

            return new ModelMapper().map(favoriteRepo.save(favorite), AddFavoriteResponseDto.class);
        } catch (Exception e) {
            throw new ServerSideException("Error create favorite", e);
        }
    }
    public Boolean delete(DeleteFavoriteRequestDto payload) {
        boolean deleted = false;

        Profile profile = profileSrv.getById(payload.getProfileId());
        Favorite favorite = getById(payload.getFavoriteId());

        try {
            if(favorite.getProfile().getId() == profile.getId()) {
                favoriteRepo.delete(favorite);
                deleted = true;
            }

            return deleted;
        } catch (Exception e) {
            throw new ServerSideException("Error in deleting favorite", e);
        }
    }
}
