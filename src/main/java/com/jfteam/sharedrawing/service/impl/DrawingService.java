package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.*;
import com.jfteam.sharedrawing.repo.IDrawingRepository;
import com.jfteam.sharedrawing.repo.ILikeDrawingRepository;
import com.jfteam.sharedrawing.repo.IRatingRepository;
import com.jfteam.sharedrawing.repo.ITagRepository;
import com.jfteam.sharedrawing.service.IDrawingService;
import com.jfteam.sharedrawing.utils.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DrawingService extends LikeService<Drawing, LikeDrawing> implements IDrawingService {

    private final ITagRepository tagRepo;
    private final IDrawingRepository drawingRepo;
    private final ILikeDrawingRepository likeDrawingRepo;
    private final IRatingRepository ratingRepo;

    protected DrawingService(ProfileService profileSrv, ITagRepository tagRepo, IDrawingRepository drawingRepo, ILikeDrawingRepository likeDrawingRepo, IRatingRepository ratingRepo) {
        super(profileSrv);
        this.tagRepo = tagRepo;
        this.drawingRepo = drawingRepo;
        this.likeDrawingRepo = likeDrawingRepo;
        this.ratingRepo = ratingRepo;
    }

    @Override
    public JpaRepository<LikeDrawing, Long> getLikeRepo() {
        return likeDrawingRepo;
    }

    @Override
    protected LikeDrawing createLikeEntity(Profile profile, Boolean liked, Drawing drawing) {
        LikeDrawing newLike = new LikeDrawing();
        newLike.setProfile(profile);
        newLike.setDrawing(drawing);
        newLike.setLiked(liked);
        return newLike;
    }

    @Override
    protected LikeDrawing getLikeByProfileIdAndEntityId(Long profileId, Long entityId) {
        return likeDrawingRepo.findByProfileIdAndDrawingId(profileId, entityId);

    }

    public LikeDrawing getDrawingLike(Long profileId, Long entityId) {
        return getLikeByProfileIdAndEntityId(profileId, entityId);
    }


    public LikeDrawing likeDrawing(Long drawingId, Boolean liked, Authentication auth) {
        return likeOrDislike(drawingId, liked, auth);
    }


    public Boolean unlikeDrawing(Long drawingId, Authentication auth) {
        return unlike(drawingId, auth);
    }

    public Rating rateDrawing(Long drawingId, Integer rate,  Authentication auth) {
        Drawing drawing = getById(drawingId);
        Profile profile = profileSrv.getByAuth(auth);

        Rating existingRate =  drawing.getRates().stream()
                .filter(_rate -> Objects.equals(_rate.getProfile().getId(), profile.getId()))
                .findFirst()
                .orElse(null);

        try {
            if(existingRate != null) {
                existingRate.setRate(rate);
                return ratingRepo.save(existingRate);
            } else {
                Rating updateRating = new Rating();
                updateRating.setProfile(profile);
                updateRating.setDrawing(drawing);
                updateRating.setRate(rate);
                return ratingRepo.save(updateRating);

            }
        } catch (Exception e) {
            throw new ServerSideException("Error during rate drawing", e);
        }
    }

    public Drawing create(Drawing drawing, List<Long> tagsId, Authentication auth) {
        Profile profile = profileSrv.getByAuth(auth);
        try {
            drawing.setProfile(profile);
            if (tagsId != null && !tagsId.isEmpty()) {
                List<Tag> tags = tagRepo.findAllById(tagsId);
                drawing.setTags(tags);
            }
            return drawingRepo.save(drawing);
        } catch (Exception e) {
            throw new ServerSideException("Error create drawing", e);
        }
    }

    public Page<Drawing> getList(Integer page, Integer size, SortDirection sortDirection, List<Long> tagIds) {
        int defaultPage = 0;
        int defaultSize = 10;

        int pageNumber = (page != null) ? page : defaultPage;
        int pageSize = (size != null) ? size : defaultSize;

        Sort.Direction direction = (sortDirection != null && sortDirection == SortDirection.DESCENDING) ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "name");

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Specification<Drawing> spec = (root, query, criteriaBuilder) -> {
            if (tagIds != null && !tagIds.isEmpty()) {
                return root.join("tags").get("id").in(tagIds);
            }
            return null;
        };
        return drawingRepo.findAll(spec, pageable);
    }
}
