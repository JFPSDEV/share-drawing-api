package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.dto.tag.AddTagReqDto;
import com.jfteam.sharedrawing.dto.tag.TagItemResDto;
import com.jfteam.sharedrawing.model.Tag;

import java.util.List;

public interface ITagService extends IGetableService<Tag> {
    public Tag create(String tagName);
}
