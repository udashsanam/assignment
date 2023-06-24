package com.project.coffeshop.controller;

import com.project.coffeshop.service.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/coffee")
public class CoffeeController extends BaseController{

    private final CoffeeService coffeeService;


    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }


    @GetMapping("get-by-id")
    public ResponseEntity<?> getByid(@RequestParam("coffeeId") Long coffeeId){
        return ResponseEntity.ok(successResponse("Successfully fetched", coffeeService.getById(coffeeId)));
    }

    @GetMapping("get-all-coffee")
    public ResponseEntity<?> getAllCoffee(@RequestParam("cafeId") Long cafeId){
        return ResponseEntity.ok(successResponse("Successfully fetched", coffeeService.getAllCoffee(cafeId)));
    }
}
