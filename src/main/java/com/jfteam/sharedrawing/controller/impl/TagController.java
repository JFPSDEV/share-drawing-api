package com.jfteam.sharedrawing.controller.impl;

import com.jfteam.sharedrawing.controller.ITagController;
import com.jfteam.sharedrawing.dto.tag.AddTagRequestDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import com.jfteam.sharedrawing.service.impl.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController extends MainController implements ITagController {
    private final String TAG_PATH = "/tag";
    private final TagService service;

    @PostMapping(AUTH_PATH + TAG_PATH +  "/add")
    public ResponseEntity<TagItemResponseDto> addTag(@RequestBody AddTagRequestDto addTagRequestDto) {
        TagItemResponseDto response = service.create(addTagRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(PUBLIC_PATH + TAG_PATH + "/list")
    public ResponseEntity<List<TagItemResponseDto>> getTagList() {
        List<TagItemResponseDto> responses = service.getList();
        return ResponseEntity.ok(responses);
    }
}
