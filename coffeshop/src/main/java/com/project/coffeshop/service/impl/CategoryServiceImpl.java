package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CategoryEntity;
import com.project.coffeshop.pojo.request.CategoryPojo;
import com.project.coffeshop.pojo.response.CategoryDto;
import com.project.coffeshop.repo.CategoryRepository;
import com.project.coffeshop.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, Long> implements CategoryService {



    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto saveCategory(CategoryPojo categoryPojo) {

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryPojo.getName());

        try {
            categoryEntity = save(categoryEntity);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Error saving category");
        }

        return new CategoryDto(categoryEntity.getId(), categoryEntity.getName());
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntities.parallelStream()
                .map(categoryEntity -> new CategoryDto(categoryEntity.getId(), categoryEntity.getName()))
                .collect(Collectors.toList());
    }
}
