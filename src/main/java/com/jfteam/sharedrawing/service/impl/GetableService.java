package com.jfteam.sharedrawing.service.impl;

import java.lang.reflect.ParameterizedType;
import com.jfteam.sharedrawing.exception.NoSuchEntityException;
import com.jfteam.sharedrawing.model.IGenericEntity;
import com.jfteam.sharedrawing.service.IGetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public abstract class GetableService<Entity extends IGenericEntity> implements IGetableService<Entity> {

    @Autowired
    public JpaRepository<Entity, Long> repository;

    @Override
    public Entity getById(Long id) {
        try {
            return repository.findById(id).get();
        }
        catch(NoSuchElementException e) {
            throw new NoSuchEntityException("There is no " + getEntityClassName() + " at the id " + id, e);
        }
    }

    @Override
    public List<Entity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("unchecked")
    private String getEntityClassName() {
        return ((Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
