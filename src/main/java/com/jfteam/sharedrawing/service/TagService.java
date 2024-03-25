package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.tag.AddTagRequestDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import com.jfteam.sharedrawing.model.Tag;
import com.jfteam.sharedrawing.repo.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    public final TagRepository tagRepo;

    private static final Logger logger = LoggerFactory.getLogger(TagService.class);

    public ResponseEntity<TagItemResponseDto> create(AddTagRequestDto addTagRequestDto) {
        Tag createTag = new Tag();
        createTag.setName(addTagRequestDto.getName());

        Tag savedTag = tagRepo.save(createTag);

        TagItemResponseDto tagItemResponse = new ModelMapper().map(savedTag, TagItemResponseDto.class);

        return ResponseEntity.ok(tagItemResponse);
    }

    public ResponseEntity<List<TagItemResponseDto>> getList() {
        List<Tag> tags = tagRepo.findAll();
        ModelMapper modelMapper = new ModelMapper();

        List<TagItemResponseDto> responses = tags.stream( )
                .map(tag -> modelMapper.map(tag, TagItemResponseDto.class))
                .toList();

        return ResponseEntity.ok(responses);
    }
}
