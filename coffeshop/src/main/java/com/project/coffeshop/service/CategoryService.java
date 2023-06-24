package com.project.coffeshop.service;

import com.project.coffeshop.entity.CategoryEntity;
import com.project.coffeshop.pojo.request.CategoryPojo;
import com.project.coffeshop.pojo.response.CategoryDto;

import java.util.List;

public interface CategoryService extends BaseService<CategoryEntity, Long> {

    CategoryDto saveCategory(CategoryPojo categoryPojo);

    List<CategoryDto> findAllCategory();
}
