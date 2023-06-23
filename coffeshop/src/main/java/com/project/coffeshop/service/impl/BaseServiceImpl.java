package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.BaseEntity;
import com.project.coffeshop.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

public  class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

    private final JpaRepository<T, ID>  repository;
    public BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }
    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Entity not found"));
    }
}
