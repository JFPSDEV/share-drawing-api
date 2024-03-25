package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.comment.AddCommentRequestDto;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.drawing.AddDrawingRequestDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingDetailsResponseDto;
import com.jfteam.sharedrawing.dto.drawing.DrawingItemResponseDto;
import com.jfteam.sharedrawing.dto.drawing.FilterDrawingRequestDto;
import com.jfteam.sharedrawing.dto.like.LikeRequestDto;
import com.jfteam.sharedrawing.model.*;
import com.jfteam.sharedrawing.repo.*;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service

public class DrawingService extends LikeService<Drawing, LikeDrawing> {

    private final TagRepository tagRepo;
    private final DrawingRepository drawingRepo;
    private final ProfileRepository profileRepo;
    private final CommentRepository commentRepo;
    private final LikeDrawingRepository likeDrawingRepo;

    protected DrawingService(ProfileRepository profileRepo, TagRepository tagRepo, DrawingRepository drawingRepo, ProfileRepository profileRepo1, CommentRepository commentRepo, LikeDrawingRepository likeDrawingRepo) {
        super(profileRepo);
        this.tagRepo = tagRepo;
        this.drawingRepo = drawingRepo;
        this.profileRepo = profileRepo1;
        this.commentRepo = commentRepo;
        this.likeDrawingRepo = likeDrawingRepo;
    }

    @Override
    public JpaRepository<LikeDrawing, Long> getLikeRepo() {
        return likeDrawingRepo;
    }

    @Override
    protected Drawing getEntityById(Long id) {
        return drawingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drawing not found with id: " + id));
    }

    @Override
    protected LikeDrawing createLikeEntity(Profile profile, Boolean liked, Drawing drawing) {
        LikeDrawing newLike = new LikeDrawing();
        newLike.setProfile(profile);
        newLike.setDrawing(drawing);
        newLike.setLiked(liked);
        return newLike;
    }


    public ResponseEntity<DrawingDetailsResponseDto> getDrawingDetails(Drawing drawing) {
        DrawingDetailsResponseDto drawingDetailsResponse = new ModelMapper().map(drawing, DrawingDetailsResponseDto.class);
        return ResponseEntity.ok(drawingDetailsResponse);
    }

    public  ResponseEntity<DrawingDetailsResponseDto> likeDraw(LikeRequestDto likeDrawingRequestDto) {
        Drawing drawing = drawingRepo.findById(likeDrawingRequestDto.getEntityId())
                .orElseThrow(() -> new IllegalArgumentException("Drawing not found with id: " + likeDrawingRequestDto.getEntityId()));

        Profile profile = profileRepo.findById(likeDrawingRequestDto.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found with id: " + likeDrawingRequestDto.getProfileId()));

        LikeDrawing existingLike = drawing.getLikes().stream()
                .filter(like -> like.getProfile().getId() == likeDrawingRequestDto.getProfileId())
                .findFirst()
                .orElse(null);

        if(existingLike != null) {
            existingLike.setLiked(likeDrawingRequestDto.getLiked());
            likeDrawingRepo.save(existingLike);
        }
        else {

            LikeDrawing newLike = new LikeDrawing();
            newLike.setProfile(profile);
            newLike.setDrawing(drawing);
            newLike.setLiked(likeDrawingRequestDto.getLiked());
            drawing.getLikes().add(newLike);
        }

        drawingRepo.save(drawing);

        return getDrawingDetails(drawing);
    }

    public  ResponseEntity<DrawingDetailsResponseDto> likeDrawing(LikeRequestDto likeRequestDto) {
        Drawing drawing = like(likeRequestDto);
        drawingRepo.save(drawing);
        return getDrawingDetails(drawing);
    }

    public ResponseEntity<DrawingDetailsResponseDto> getById(Long id) {
        Drawing drawing = drawingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drawing not found with id: " + id));

        return getDrawingDetails(drawing);
    }

    public ResponseEntity<DrawingItemResponseDto> create(AddDrawingRequestDto addDrawingRequestDto) {
        Profile profile = profileRepo.findById(addDrawingRequestDto.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + addDrawingRequestDto.getProfileId()));

        Drawing drawing = new ModelMapper().map(addDrawingRequestDto.getDrawing(), Drawing.class);
        drawing.setProfile(profile);

        if (addDrawingRequestDto.getTagIds() != null && !addDrawingRequestDto.getTagIds().isEmpty()) {
            List<Tag> tags = tagRepo.findAllById(addDrawingRequestDto.getTagIds());
            drawing.setTags(tags);
        }

        Drawing savedDrawing = drawingRepo.save(drawing);
        DrawingItemResponseDto drawingItemResponseDto = new ModelMapper().map(savedDrawing, DrawingItemResponseDto.class);

        return ResponseEntity.ok(drawingItemResponseDto);
    }

    public ResponseEntity<CommentDto> comment(AddCommentRequestDto addCommentRequestDto) {
        Profile profile = profileRepo.findById(addCommentRequestDto.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + addCommentRequestDto.getProfileId()));

        Drawing drawing = drawingRepo.findById(addCommentRequestDto.getDrawingId())
                .orElseThrow(() -> new IllegalArgumentException("Drawing not found with id: " + addCommentRequestDto.getDrawingId()));

        Comment newComment = Comment.builder()
                .message(addCommentRequestDto.getMessage())
                .profile(profile)
                .drawing(drawing)
                .build();

        Comment savedComment = commentRepo.save(newComment);

        CommentDto commentDto = new ModelMapper().map(savedComment, CommentDto.class);

        return ResponseEntity.ok(commentDto);
    }

    public ResponseEntity<List<DrawingItemResponseDto>> getList(Optional<FilterDrawingRequestDto> filterDtoOpt) {

        int defaultPage = 0;
        int defaultSize = 10;

        Integer page = filterDtoOpt.map(FilterDrawingRequestDto::getPage).orElse(defaultPage);
        Integer size = filterDtoOpt.map(FilterDrawingRequestDto::getSize).orElse(defaultSize);

        SortDirection sortDir = filterDtoOpt.map(FilterDrawingRequestDto::getSortDirection).orElse(SortDirection.DESCENDING);

        final String sortName = "name";

        Sort sort = Sort.by(sortName).ascending();
        if (sortDir == SortDirection.DESCENDING) {
            sort = Sort.by(sortName).descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Drawing> spec = (root, query, criteriaBuilder) -> {
            if (filterDtoOpt.isPresent() && filterDtoOpt.get().getTagIds() != null && !filterDtoOpt.get().getTagIds().isEmpty()) {
                return root.join("tags").get("id").in(filterDtoOpt.get().getTagIds());
            }
            return null;
        };

        Page<Drawing> drawingPage = drawingRepo.findAll(spec, pageable);

        List<DrawingItemResponseDto> responses = drawingPage.getContent().stream()
                .map(drawing -> new ModelMapper().map(drawing, DrawingItemResponseDto.class))
                .toList();

        return ResponseEntity.ok(responses);
    }

}
