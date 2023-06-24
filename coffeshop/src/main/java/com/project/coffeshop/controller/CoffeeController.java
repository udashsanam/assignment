package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.CoffeePojo;
import com.project.coffeshop.service.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/coffee")
public class CoffeeController extends BaseController{

    private final CoffeeService coffeeService;


    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CoffeePojo coffeePojo){
        return ResponseEntity.ok(successResponse("Successfully saved", coffeeService.saveCoffee(coffeePojo)));
    }


    @GetMapping("get-by-id")
    public ResponseEntity<?> getByid(@RequestParam("coffeeId") Long coffeeId){
        return ResponseEntity.ok(successResponse("Successfully fetched", coffeeService.getById(coffeeId)));
    }

    @GetMapping("get-all-coffee-cafe")
    public ResponseEntity<?> getAllCoffeeCafe(@RequestParam("cafeId") Long cafeId){
        return ResponseEntity.ok(successResponse("Successfully fetched", coffeeService.getAllCoffee(cafeId)));
    }



}
