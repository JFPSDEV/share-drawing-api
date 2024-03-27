package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.dto.tag.AddTagRequestDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResponseDto;
import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.Tag;
import com.jfteam.sharedrawing.repo.ITagRepository;
import com.jfteam.sharedrawing.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService extends GetableService<Tag> implements ITagService {
    public final ITagRepository tagRepo;

    public TagItemResponseDto create(AddTagRequestDto addTagRequestDto) {
        Tag createTag = new Tag();
        createTag.setName(addTagRequestDto.getName());

        try {
            Tag savedTag = tagRepo.save(createTag);
            return  new ModelMapper().map(savedTag, TagItemResponseDto.class);
        } catch (Exception e) {
            throw new ServerSideException("Error create tag", e);
        }
    }

    public List<TagItemResponseDto> getList() {
        List<Tag> tags = tagRepo.findAll();
        ModelMapper modelMapper = new ModelMapper();

        return tags.stream( )
                .map(tag -> modelMapper.map(tag, TagItemResponseDto.class))
                .toList();

    }
}
