package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.CategoryEntity;
import com.project.coffeshop.entity.CoffeeEntity;
import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.enums.Role;
import com.project.coffeshop.exception.UnAuthorizeException;
import com.project.coffeshop.pojo.request.CoffeePojo;
import com.project.coffeshop.pojo.response.CoffeeDto;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.repo.CategoryRepository;
import com.project.coffeshop.repo.CoffeeRepository;
import com.project.coffeshop.service.CoffeeService;
import com.project.coffeshop.service.UserTokenService;
import com.project.coffeshop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoffeeServiceImpl extends BaseServiceImpl<CoffeeEntity, Long> implements CoffeeService {


    private final CoffeeRepository coffeeRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final UserTokenService userTokenService;


    public CoffeeServiceImpl(CoffeeRepository coffeeRepository, UserTokenService userTokenService) {
        super(coffeeRepository);
        this.coffeeRepository = coffeeRepository;
        this.userTokenService = userTokenService;
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

    @Override
    public CoffeeDto saveCoffee(CoffeePojo coffeePojo, String token) {
        String role = userTokenService.getClaims(token).get(Constants.USER_ROLES_CLAIM).toString();
        UserEntity user = userTokenService.getUser(token);
        if(role.contains(Role.CAFE_OWNER.toString())) throw new  UnAuthorizeException("your are not authorize to add coffee");
        CafeEntity cafe  = cafeRepository.findById(coffeePojo.getCafeId()).orElseThrow(()-> new RuntimeException("Cafe not found"));
        if(!cafe.getId().equals(user.getCafe().getId())) throw new UnAuthorizeException("Your are not allowed to add coffee");
        CategoryEntity categoryEntity = categoryRepository.findById(coffeePojo.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
        CoffeeEntity coffee = CoffeeEntity.builder()
                .name(coffeePojo.getName())
                .price(coffeePojo.getPrice())
                .cafe(cafe)
                .category(categoryEntity)
                .build();
        try {
            coffee = save(coffee);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Error adding coffee");
        }
        return new CoffeeDto(coffee.getId(), coffee.getName(), categoryEntity.getName());
    }
}
