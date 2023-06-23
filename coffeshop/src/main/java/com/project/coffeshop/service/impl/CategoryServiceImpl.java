package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CategoryEntity;
import com.project.coffeshop.repo.CategoryRepository;
import com.project.coffeshop.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, Long> implements CategoryService {



    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }
}
