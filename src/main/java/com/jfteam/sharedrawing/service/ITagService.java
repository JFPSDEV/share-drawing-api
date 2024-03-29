package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.model.Tag;

public interface ITagService extends IGetableService<Tag> {
    public Tag create(String tagName);
}
