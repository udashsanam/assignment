package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.CategoryPojo;
import com.project.coffeshop.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CategoryPojo categoryPojo){
        return ResponseEntity.ok(successResponse("successfully saved", categoryService.saveCategory(categoryPojo)));
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(successResponse("Successfully fetched", categoryService.findAllCategory()));
    }
}
