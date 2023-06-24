package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.*;
import com.project.coffeshop.enums.OrderStatus;
import com.project.coffeshop.pojo.request.OrderPojo;
import com.project.coffeshop.pojo.response.OrderDto;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.repo.CoffeeRepository;
import com.project.coffeshop.repo.OrderRepository;
import com.project.coffeshop.repo.UserRepository;
import com.project.coffeshop.service.OrderService;
import com.project.coffeshop.service.UserTokenService;
import com.project.coffeshop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<OrderEntity, Long> implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    private final UserTokenService userTokenService;

    @Autowired
    private UserRepository userRepository;



    public OrderServiceImpl(OrderRepository orderRepository, UserTokenService userTokenService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.userTokenService = userTokenService;
    }

    @Override
    public OrderDto create(OrderPojo orderPojo, String token) {
        UserEntity user = userTokenService.getUser(token);
        CafeEntity cafeEntity = cafeRepository.findById(orderPojo.getCafeId()).orElseThrow(()-> new RuntimeException("Cafe not found"));
        Map<Long, Integer> coffeeQuantity = orderPojo.getCoffeeQuantityMap();
        Map<Long, String> coffeeInstruction = orderPojo.getCoffeeInstruction();
        double orderTotal = 0.0;

        OrderEntity order = new OrderEntity();
        List<OrderDetailEntity>  orderDetailEntities = new ArrayList<>();
        order.setCafe(cafeEntity);

        for (Map.Entry<Long, Integer> coffeeOrder:
             coffeeQuantity.entrySet()) {
            CoffeeEntity coffee = coffeeRepository.findById(coffeeOrder.getKey()).orElseThrow(()-> new RuntimeException("Coffee not found"));
            orderTotal =+ coffeeOrder.getValue() * coffee.getPrice();
            OrderDetailEntity orderDetailEntity = OrderDetailEntity.builder()
                    .quantity(coffeeOrder.getValue())
                    .coffee(coffee)
                    .description(coffeeInstruction.get(coffeeOrder.getKey()))
                    .build();
            orderDetailEntities.add(orderDetailEntity);

        }
        order.setOrderTotal(orderTotal);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDetailEntities(orderDetailEntities);
        order.setUser(user);

        try {
            order =save(order);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("error placing order");
        }

        return new OrderDto(order.getId(), OrderStatus.PENDING,  orderTotal);
    }

    @Override
    public List<OrderDto> getAllOrders(String token) {
        UserEntity user = userTokenService.getUser(token);
        List<OrderEntity> orderEntities = orderRepository.findAllByUserId(user.getId());
        List<OrderDto> orderDtos = orderEntities.stream().map(orderEntity -> {

            OrderDto orderDto = new OrderDto();
            orderDto.setOrderTotal(orderEntity.getOrderTotal());
            orderDto.setOrderStatus(orderEntity.getStatus());
            HashMap<Long, String> coffeeNameMap = new HashMap<>();
            HashMap<Long, Integer> coffeeQuantity = new HashMap<>();
            orderEntity.getOrderDetailEntities().forEach(orderDetailEntity -> {
                coffeeNameMap.put(orderDetailEntity.getCoffee().getId(), orderDetailEntity.getCoffee().getName());
                coffeeQuantity.put(orderDetailEntity.getCoffee().getId(), orderDetailEntity.getQuantity());
            });
            orderDto.setCoffeeName(coffeeNameMap);
            orderDto.setCoffeeQuantity(coffeeQuantity);
            return orderDto;
        }).collect(Collectors.toList());

        return orderDtos;
    }
}
