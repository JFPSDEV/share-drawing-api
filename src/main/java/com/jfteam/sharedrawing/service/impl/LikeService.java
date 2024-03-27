package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.dto.like.UnlikeRequestDto;
import com.jfteam.sharedrawing.exception.NoSuchEntityException;
import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class LikeService<Entity extends Like<LEntity> & IGenericEntity, LEntity extends LikeEntity> extends GetableService<Entity> {
    private final ProfileService profileSrv;
     public abstract JpaRepository<LEntity, Long> getLikeRepo();

    protected LikeService(ProfileService profileSrv) {
        this.profileSrv = profileSrv;
    }

    protected abstract LEntity createLikeEntity(Profile profile, Boolean liked, Entity generic);

    protected abstract LEntity getLikeByProfileIdAndEntityId(Long profileId, Long entityId);

    public LEntity like(LikeRequestDto payload) {
        Entity entity = getById(payload.getEntityId());
        Profile profile = profileSrv.getById(payload.getProfileId());

        LEntity existingLike = (LEntity) entity.getLikes().stream()
                .filter(like -> like.getProfile().getId() == payload.getProfileId())
                .findFirst()
                 .orElse(null);

        try {
            if(existingLike != null) {
                existingLike.setLiked(payload.getLiked());
                return getLikeRepo().save(existingLike);
            } else {
                LEntity newLike = createLikeEntity(profile, payload.getLiked(), entity);
                return getLikeRepo().save(newLike);
            }
        } catch (Exception e) {
            throw new ServerSideException("Error create like on the entity", e);
        }
    }

    public Boolean unlike(UnlikeRequestDto payload) {
        boolean deleted = false;

        Profile profile = profileSrv.getById(payload.getProfileId());

        LikeEntity likeEntity = getLikeRepo().findById(payload.getEntityId())
                .orElseThrow(() -> new NoSuchEntityException("Entity not found with id: " + payload.getEntityId()));

        try {
            if(likeEntity.getProfile().getId() == profile.getId()) {
                getLikeRepo().delete((LEntity) likeEntity);
                deleted = true;
            }
            return deleted;
        } catch (Exception e) {
            throw new ServerSideException("Error deleted like on the entity", e);
        }
    }
}
