package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.*;
import com.project.coffeshop.enums.OrderStatus;
import com.project.coffeshop.pojo.request.OrderPojo;
import com.project.coffeshop.pojo.response.OrderDto;
import com.project.coffeshop.repo.*;
import com.project.coffeshop.service.UserTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(OrderServiceImpl.class)
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private  OrderRepository orderRepository;

    @MockBean
    private CafeRepository cafeRepository;

    @MockBean
    private CoffeeRepository coffeeRepository;

    @MockBean
    private  UserTokenService userTokenService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserRoleRepository userRoleRepository;

    @MockBean
    private OrderDetailRepository orderDetailRepository;

    @Test
    @DisplayName("Order place test")
    void create() {

        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setCafeId(1l);
        HashMap<Long, Integer> orderQuantityMap = new HashMap<>();
        HashMap<Long, String> orderDescriptionMap = new HashMap<>();
        orderQuantityMap.put(1l, 2);
        orderQuantityMap.put(2l, 1);

        orderDescriptionMap.put(1l, "testing ");
        orderDescriptionMap.put(2l, "test 2");
        orderPojo.setCoffeeInstruction(orderDescriptionMap);
        orderPojo.setCoffeeQuantityMap(orderQuantityMap);

        String token = "access token";

        UserEntity user = new UserEntity();
        user.setId(1l);

        Mockito.when(userTokenService.getUser("access token")).thenReturn(user);

        CafeEntity cafeEntity = new CafeEntity();
        cafeEntity.setId(1l);

        Mockito.when(cafeRepository.findById(1l)).thenReturn(Optional.of(cafeEntity));
        CoffeeEntity coffee = new CoffeeEntity();
        coffee.setPrice(1.0d);

        Mockito.when(coffeeRepository.findById(1l)).thenReturn(Optional.of(coffee));
        Mockito.when(coffeeRepository.findById(2l)).thenReturn(Optional.of(coffee));

        Mockito.when(orderDetailRepository.saveAll(any())).thenReturn(null);

        OrderEntity order = new OrderEntity();
        order.setId(1l);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCafe(cafeEntity);

        Mockito.when(orderRepository.save(order)).thenReturn(order);

        Mockito.when(orderRepository.save(order)).thenReturn(order);

        OrderDto orderDto = orderService.create(orderPojo, "access token");

        assertEquals(3.0, orderDto.getOrderTotal());
        assertEquals(1l, orderDto.getOrderId());
        assertEquals(OrderStatus.PENDING, orderDto.getOrderStatus());




    }

    @Test
    @DisplayName("get all order of user ")
    void getAllOrders() {

    }

    @Test
    void getAllOrderCafe() {
    }

    @Test
    void updateOrder() {
    }
}