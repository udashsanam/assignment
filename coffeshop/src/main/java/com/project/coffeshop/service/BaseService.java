package com.project.coffeshop.service;

import java.util.List;

public interface BaseService<T, ID> {

    T save(T t);

    T update(T t);

    T findById(ID id);

}
