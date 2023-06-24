package com.project.coffeshop.service;

import com.project.coffeshop.entity.CoffeeEntity;
import com.project.coffeshop.pojo.request.CoffeePojo;
import com.project.coffeshop.pojo.response.CafeDto;
import com.project.coffeshop.pojo.response.CoffeeDto;

import java.util.List;

public interface CoffeeService extends BaseService<CoffeeEntity, Long> {
    CoffeeDto getById(Long coffeeId);

    List<CoffeeDto> getAllCoffee(Long cafeId);

    CoffeeDto saveCoffee(CoffeePojo coffeePojo);
}
