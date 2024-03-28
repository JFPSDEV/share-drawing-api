package com.jfteam.sharedrawing.service.impl;

import com.jfteam.sharedrawing.exception.ServerSideException;
import com.jfteam.sharedrawing.model.Tag;
import com.jfteam.sharedrawing.repo.ITagRepository;
import com.jfteam.sharedrawing.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TagService extends GetableService<Tag> implements ITagService {
    public final ITagRepository tagRepo;

    public Tag create(String tagName) {
        Tag createTag = new Tag();
        createTag.setName(tagName);
        try {
            return tagRepo.save(createTag);
        } catch (Exception e) {
            throw new ServerSideException("Error creating tag", e);
        }
    }
}
