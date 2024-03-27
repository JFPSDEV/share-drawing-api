package com.jfteam.sharedrawing.service;

import java.util.List;
import com.jfteam.sharedrawing.model.IGenericEntity;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface IGetableService<E extends IGenericEntity> {

    public E getById(Long id);
    public List<E> getAll();

}
