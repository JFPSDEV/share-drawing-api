package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.IGenericEntity;
import com.jfteam.sharedrawing.model.Like;
import com.jfteam.sharedrawing.model.LikeEntity;
import com.jfteam.sharedrawing.model.Profile;
import com.jfteam.sharedrawing.service.IProfileService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public abstract class LikeService<Entity extends Like<LEntity> & IGenericEntity, LEntity extends LikeEntity> extends GetableService<Entity> {
    protected final IProfileService profileSrv;
    public abstract JpaRepository<LEntity, Long> getLikeRepo();

    protected LikeService(ProfileService profileSrv) {
        this.profileSrv = profileSrv;
    }

    protected abstract LEntity createLikeEntity(Profile profile, Boolean liked, Entity generic);

    protected abstract LEntity getLikeByProfileIdAndEntityId(Long profileId, Long entityId);

    public LEntity getLikeEntity(Entity entity, Long profileId) {
        return entity.getLikes().stream()
                .filter(like -> Objects.equals(like.getProfile().getId(), profileId))
                .findFirst()
                .orElse(null);
    }

    public LEntity likeOrDislike(Long entityId, Boolean liked, Authentication auth) {
        Entity entity = getById(entityId);
        Profile profile = profileSrv.getByAuth(auth);
        LEntity existingLike = getLikeEntity(entity, profile.getId());

        try {
            if(existingLike != null) {
                existingLike.setLiked(liked);
                return getLikeRepo().save(existingLike);
            } else {
                LEntity newLike = createLikeEntity(profile, liked, entity);
                return getLikeRepo().save(newLike);
            }
        } catch (Exception e) {
            throw new ServerSideException("Error create like on the entity", e);
        }
    }

    public Boolean unlike(Long entityId, Authentication auth) {
        boolean deleted = false;

        Entity entity = getById(entityId);
        Profile profile = profileSrv.getByAuth(auth);
        LEntity existingLike = getLikeEntity(entity, profile.getId());

        try {
            if(existingLike != null) {
                getLikeRepo().delete(existingLike);
                deleted = true;
            }
            return deleted;
        } catch (Exception e) {
            throw new ServerSideException("Error deleted like on the entity", e);
        }
    }
}
