package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.CafePojo;
import com.project.coffeshop.service.CafeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cafe")
public class CafeController extends BaseController{

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }


    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CafePojo cafePojo, HttpServletRequest request){
        String token = getAccessToken(request);
        return ResponseEntity.ok(successResponse("successfully saved Cafe", cafeService.saveCafe(cafePojo,token)));
    }

    @GetMapping("get-all-cafe")
    public ResponseEntity<?> getAllCafe(){
        return ResponseEntity.ok(successResponse("Successfully fetched", cafeService.findAllCafe()));
    }


}
