package com.jfteam.sharedrawing.controller;

import com.jfteam.sharedrawing.dto.tag.AddTagRequestDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITagController {

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Create a new Tag")
    public ResponseEntity<TagItemResponseDto> addTag(@RequestBody AddTagRequestDto addTagRequestDto);

    @ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get all Tags")
    public ResponseEntity<List<TagItemResponseDto>> getTagList();
}
