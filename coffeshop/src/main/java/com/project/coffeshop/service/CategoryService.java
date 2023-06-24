package com.project.coffeshop.service;

import com.project.coffeshop.entity.CategoryEntity;
import com.project.coffeshop.pojo.request.CategoryPojo;
import com.project.coffeshop.pojo.response.CategoryDto;

public interface CategoryService extends BaseService<CategoryEntity, Long> {
    CategoryDto saveCategory(CategoryPojo categoryPojo);
}
