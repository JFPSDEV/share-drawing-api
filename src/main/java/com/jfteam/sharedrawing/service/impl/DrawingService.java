package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.drawing.*;
import com.jfteam.sharedrawing.dto.like.*;
import com.jfteam.sharedrawing.dto.rating.RatingResponseDto;
import com.jfteam.sharedrawing.dto.rating.UpsertRatingRequestDto;
import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.*;
import com.jfteam.sharedrawing.repo.*;
import com.jfteam.sharedrawing.service.IDrawingService;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;


@Service
public class DrawingService extends LikeService<Drawing, LikeDrawing> implements IDrawingService {

    private final ITagRepository tagRepo;
    private final IDrawingRepository drawingRepo;
    private final ProfileService profileSrv;
    private final ILikeDrawingRepository likeDrawingRepo;
    private final IRatingRepository ratingRepo;
    private final RatingService ratingService;

    protected DrawingService(ProfileService profileSrv, ITagRepository tagRepo, IDrawingRepository drawingRepo, ProfileService profileSrv1, ILikeDrawingRepository likeDrawingRepo, IRatingRepository ratingRepo, RatingService ratingService) {
        super(profileSrv);
        this.tagRepo = tagRepo;
        this.drawingRepo = drawingRepo;
        this.profileSrv = profileSrv1;
        this.likeDrawingRepo = likeDrawingRepo;
        this.ratingRepo = ratingRepo;
        this.ratingService = ratingService;
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

    public LikeDrawingResponseDto getDrawingLike(Long profileId, Long entityId) {
        LikeDrawing likeDrawing = getLikeByProfileIdAndEntityId(profileId, entityId);
        if(likeDrawing != null) {
            return  new ModelMapper().map(likeDrawing, LikeDrawingResponseDto.class);
        }
        return null;
    }

    public DrawingDetailsResponseDto getDrawingDetails(Drawing drawing) {
       return new ModelMapper().map(drawing, DrawingDetailsResponseDto.class);
    }

    public  LikeDrawingResponseDto likeDrawing(LikeRequestDto payload) {
        return new ModelMapper().map(like(payload), LikeDrawingResponseDto.class);
    }


    public Boolean unlikeDrawing(UnlikeRequestDto payload) {
        return unlike(payload);
    }

    public RatingResponseDto rateDrawing(UpsertRatingRequestDto payload) {
        Drawing drawing = getById(payload.getDrawingId());
        Profile profile = profileSrv.getById(payload.getProfileId());

        Rating existingRate =  drawing.getRates().stream()
                .filter(rate -> rate.getProfile().getId() == profile.getId())
                .findFirst()
                .orElse(null);

        try {
            if(existingRate != null) {
                existingRate.setRate(payload.getRate());
                Rating saveRating = ratingRepo.save(existingRate);
                return ratingService.getRatingDetails(saveRating);
            } else {
                Rating updateRating = new Rating();
                updateRating.setProfile(profile);
                updateRating.setDrawing(drawing);
                updateRating.setRate(payload.getRate());
                Rating saveRating = ratingRepo.save(updateRating);
                return ratingService.getRatingDetails(saveRating);
            }
        } catch (Exception e) {
            throw new ServerSideException("Error during rate drawing", e);
        }
    }

    public DrawingByIdResponseDto getById(Long id, Authentication auth) {
        Drawing drawing = getById(id);
        DrawingDetailsResponseDto drawingRes = new ModelMapper().map(drawing, DrawingDetailsResponseDto.class);

        Profile profile = profileSrv.getByAuth(auth);

        if(profile != null) {
            RatingResponseDto ratingRes = ratingService.findByProfileIdAndDrawingId(profile.getId(), id);
            LikeDrawingResponseDto likeRes = getDrawingLike(profile.getId(), id);
            return DrawingByIdResponseDto
                    .builder()
                    .drawing(drawingRes)
                    .rating(ratingRes)
                    .likeDrawing(likeRes)
                    .build();
        }
        return DrawingByIdResponseDto.builder().drawing(drawingRes).build();
    }

    public DrawingItemResponseDto create(AddDrawingRequestDto payload) {
        Profile profile = profileSrv.getById(payload.getProfileId());

        try {
            Drawing drawing = new ModelMapper().map(payload.getDrawing(), Drawing.class);
            drawing.setProfile(profile);

            if (payload.getTagIds() != null && !payload.getTagIds().isEmpty()) {
                List<Tag> tags = tagRepo.findAllById(payload.getTagIds());
                drawing.setTags(tags);
            }

            Drawing savedDrawing = drawingRepo.save(drawing);
            return new ModelMapper().map(savedDrawing, DrawingItemResponseDto.class);
        } catch (Exception e) {
            throw new ServerSideException("Error create drawing", e);
        }
    }

    public List<DrawingItemResponseDto> getList(Optional<FilterDrawingRequestDto> payload) {
        int defaultPage = 0;
        int defaultSize = 10;

        Integer page = payload.map(FilterDrawingRequestDto::getPage).orElse(defaultPage);
        Integer size = payload.map(FilterDrawingRequestDto::getSize).orElse(defaultSize);

        SortDirection sortDir = payload.map(FilterDrawingRequestDto::getSortDirection).orElse(SortDirection.DESCENDING);

        final String sortName = "name";

        Sort sort = Sort.by(sortName).ascending();
        if (sortDir == SortDirection.DESCENDING) {
            sort = Sort.by(sortName).descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Drawing> spec = (root, query, criteriaBuilder) -> {
            if (payload.isPresent() && payload.get().getTagIds() != null && !payload.get().getTagIds().isEmpty()) {
                return root.join("tags").get("id").in(payload.get().getTagIds());
            }
            return null;
        };

        Page<Drawing> drawingPage = drawingRepo.findAll(spec, pageable);

        return drawingPage.getContent().stream()
                .map(drawing -> new ModelMapper().map(drawing, DrawingItemResponseDto.class))
                .toList();
    }

}
