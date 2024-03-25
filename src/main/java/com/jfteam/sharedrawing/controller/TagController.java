package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.tag.AddTagRequestDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import com.jfteam.sharedrawing.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController extends MainController {
    private final String TAG_PATH = "/tag";
    private final TagService service;

    @PostMapping(AUTH_PATH + TAG_PATH +  "/add")
    public ResponseEntity<TagItemResponseDto> addTag(@RequestBody AddTagRequestDto addTagRequestDto) {
        return service.create(addTagRequestDto);
    }

    @GetMapping(PUBLIC_PATH + TAG_PATH + "/list")
    public ResponseEntity<List<TagItemResponseDto>> getTagList() {
        return service.getList();
    }
}
