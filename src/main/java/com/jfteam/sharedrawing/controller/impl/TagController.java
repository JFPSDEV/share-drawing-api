package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.controller.ITagController;
import com.jfteam.sharedrawing.dto.favorite.AddFavoriteResDto;
import com.jfteam.sharedrawing.dto.tag.AddTagReqDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResDto;
import com.jfteam.sharedrawing.model.Tag;
import com.jfteam.sharedrawing.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController extends AbstractController implements ITagController {
    private final String TAG_PATH = "/tag";
    private final ITagService service;

    @PostMapping(AUTH_PATH + TAG_PATH +  "/add")
    public ResponseEntity<TagItemResDto> addTag(@RequestBody AddTagReqDto addTagReqDto) {
        return ResponseEntity.ok(
                mapEntityToDto(
                        service.create(addTagReqDto.getName()),
                        TagItemResDto.class
                )
        );
    }

    @GetMapping(PUBLIC_PATH + TAG_PATH + "/list")
    public ResponseEntity<List<TagItemResDto>> getTagList() {
        return ResponseEntity.ok(
                service.getAll().stream()
                        .map(tag -> mapEntityToDto(tag, TagItemResDto.class))
                        .toList()
        );
    }
}
