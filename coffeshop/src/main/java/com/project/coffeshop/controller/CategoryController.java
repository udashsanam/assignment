package com.project.coffeshop.controller;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.CategoryEntity;
import com.project.coffeshop.pojo.request.CategoryPojo;
import com.project.coffeshop.service.CafeService;
import com.project.coffeshop.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class CategoryController extends BaseController {

    private CategoryService categoryService;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CategoryPojo categoryPojo){
        return ResponseEntity.ok(successResponse("successfully saved", categoryService.saveCategory(categoryPojo)));
    }
}
