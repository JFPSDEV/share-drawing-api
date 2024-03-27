package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.tag.AddTagRequestDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ITagService {
    public TagItemResponseDto create(AddTagRequestDto addTagRequestDto);

    public List<TagItemResponseDto> getList();
}
