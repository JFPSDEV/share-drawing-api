package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.model.Like;
import com.jfteam.sharedrawing.model.LikeEntity;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.repo.ProfileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public abstract class LikeService<T extends Like<U>, U extends LikeEntity> {

    private static final Logger logger = LoggerFactory.getLogger(LikeService.class);
    private final ProfileRepository profileRepo;
    public abstract JpaRepository<U, Long> getLikeRepo();

    protected LikeService(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    protected abstract T getEntityById(Long id);

    protected abstract U createLikeEntity(Profile profile, Boolean liked, T generic);


    public T like(LikeRequestDto likeRequestDto) {
        T entity = getEntityById(likeRequestDto.getEntityId());

        Profile profile = profileRepo.findById(likeRequestDto.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found with id: " + likeRequestDto.getProfileId()));

        U existingLike = (U) entity.getLikes().stream()
                .filter(like -> like.getProfile().getId() == likeRequestDto.getProfileId())
                .findFirst()
                 .orElse(null);




        if(existingLike != null) {
            existingLike.setLiked(likeRequestDto.getLiked());
            getLikeRepo().save(existingLike);
        } else {

            U newLike = createLikeEntity(profile, likeRequestDto.getLiked(), entity);
            entity.getLikes().add(newLike);
        }

        return entity;
    }
}
