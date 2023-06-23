package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CoffeeEntity;
import com.project.coffeshop.repo.CoffeeRepository;
import com.project.coffeshop.service.CoffeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoffeeServiceImpl extends BaseServiceImpl<CoffeeEntity, Long> implements CoffeeService {


    private final CoffeeRepository coffeeRepository;


    public CoffeeServiceImpl(CoffeeRepository coffeeRepository) {
        super(coffeeRepository);
        this.coffeeRepository = coffeeRepository;
    }
}
