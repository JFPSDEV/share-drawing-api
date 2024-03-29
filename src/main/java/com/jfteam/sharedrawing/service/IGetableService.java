package com.jfteam.sharedrawing.service;

import com.jfteam.sharedrawing.model.IGenericEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IGetableService<E extends IGenericEntity> {

    public E getById(Long id);
    public List<E> getAll();

}
