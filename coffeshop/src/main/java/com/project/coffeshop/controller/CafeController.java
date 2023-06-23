package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.CafePojo;
import com.project.coffeshop.service.CafeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cafe")
public class CafeController extends BaseController{

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }


    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CafePojo cafePojo){
        return ResponseEntity.ok(successResponse("successfully saved Cafe", cafeService.saveCafe(cafePojo)));
    }


}
