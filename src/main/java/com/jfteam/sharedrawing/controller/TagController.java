package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.tag.AddTagReqDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResDto;
import com.jfteam.sharedrawing.model.Tag;
import com.jfteam.sharedrawing.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController extends AbstractController {
    private final String TAG_PATH = "/tag";
    private final ITagService service;

    @GetMapping(PUBLIC_PATH + TAG_PATH +  "/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping(AUTH_PATH + TAG_PATH +  "/add")
    public ResponseEntity<TagItemResDto> addTag(@RequestBody AddTagReqDto addTagReqDto) {
        TagItemResDto response = mapModel(
                service.create(addTagReqDto.getName()),
                TagItemResDto.class
        );

        return ResponseEntity.created(URI.create(PUBLIC_PATH + TAG_PATH +"/" + response.getId()))
                .body(response);
    }

    @GetMapping(PUBLIC_PATH + TAG_PATH + "/list")
    public ResponseEntity<List<TagItemResDto>> getTagList() {
        return ResponseEntity.ok(
                service.getAll().stream()
                        .map(tag -> mapModel(tag, TagItemResDto.class))
                        .toList()
        );
    }
}
