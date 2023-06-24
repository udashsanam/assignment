package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CoffeeEntity;
import com.project.coffeshop.pojo.response.CafeDto;
import com.project.coffeshop.pojo.response.CoffeeDto;
import com.project.coffeshop.repo.CoffeeRepository;
import com.project.coffeshop.service.CoffeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoffeeServiceImpl extends BaseServiceImpl<CoffeeEntity, Long> implements CoffeeService {


    private final CoffeeRepository coffeeRepository;


    public CoffeeServiceImpl(CoffeeRepository coffeeRepository) {
        super(coffeeRepository);
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public CoffeeDto getById(Long coffeeId) {
        CoffeeEntity coffee = findById(coffeeId);
        return new CoffeeDto(coffee.getId(), coffee.getName(), coffee.getCategory().getName());
    }


    //todo: paginate
    @Override
    public List<CoffeeDto> getAllCoffee(Long cafeId) {
        List<CoffeeEntity> coffeeEntities = coffeeRepository.findAllByCafeId(cafeId);
        List<CoffeeDto> coffeeDtos = coffeeEntities.parallelStream().map(coffeeEntity -> new CoffeeDto(coffeeEntity.getId(), coffeeEntity.getName(), coffeeEntity.getCategory().getName())).collect(Collectors.toList());
        return coffeeDtos;
    }
}
