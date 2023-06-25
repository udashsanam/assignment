package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.*;
import com.project.coffeshop.enums.OrderStatus;
import com.project.coffeshop.enums.Role;
import com.project.coffeshop.pojo.request.OrderPojo;
import com.project.coffeshop.pojo.response.OrderDetailDto;
import com.project.coffeshop.pojo.response.OrderDto;
import com.project.coffeshop.repo.*;
import com.project.coffeshop.service.UserTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

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

        String token = "test";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1l);

        List<OrderEntity> orderEntities = new ArrayList<>();

        OrderEntity order1 = new OrderEntity();
        order1.setOrderTotal(2.0d);
        order1.setStatus(OrderStatus.PENDING);

        List<OrderDetailEntity> orderDetailEntities1 = new ArrayList<>();
        OrderDetailEntity orderDetailEntity11 = new OrderDetailEntity();
        orderDetailEntity11.setQuantity(1);
        CoffeeEntity coffee1 = new CoffeeEntity();
        coffee1.setName("coffee1");
        coffee1.setId(1l);
        orderDetailEntity11.setCoffee(coffee1);
        orderDetailEntities1.add(orderDetailEntity11);

        OrderDetailEntity orderDetailEntity2 = new OrderDetailEntity();
        orderDetailEntity2.setQuantity(1);
        CoffeeEntity  coffee2 = new CoffeeEntity();
        coffee2.setId(2l);
        coffee2.setName("coffee2");
        orderDetailEntity2.setCoffee(coffee2);
        orderDetailEntities1.add(orderDetailEntity2);
        order1.setOrderDetailEntities(orderDetailEntities1);

        orderEntities.add(order1);

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setOrderTotal(1.0d);

        List<OrderDetailEntity> orderDetailEntities2 =new ArrayList<>();
        OrderDetailEntity orderDetailEntity21 = new OrderDetailEntity();
        orderDetailEntity21.setQuantity(1);
        orderDetailEntity21.setCoffee(coffee1);
        orderDetailEntities2.add(orderDetailEntity21);

        orderEntity2.setOrderDetailEntities(orderDetailEntities2);
        orderEntities.add(orderEntity2);


        Mockito.when(userTokenService.getUser(token)).thenReturn(userEntity);

        Mockito.when(orderRepository.findAllByUserId(1l)).thenReturn(orderEntities);

        List<OrderDto> orderDtos = orderService.getAllOrders(token);

        assertEquals(2, orderDtos.size());
        List<OrderDetailDto> orderDetailDtoList = orderDtos.get(0).getOrderDetailDtoList();
        List<OrderDetailDto> orderDetailDtoList1 = orderDtos.get(1).getOrderDetailDtoList();

        assertEquals(2, orderDetailDtoList.size());
        assertEquals(1, orderDetailDtoList1.size());

    }

    @Test
    @DisplayName("testing get all order by cafe owner")
    void getAllOrderCafe() {


        String token = "test";
        Long cafeId= 1l;
        UserEntity cafeOwner = new UserEntity();
        cafeOwner.setId(1l);

        CafeEntity cafeEntity = new CafeEntity();
        cafeEntity.setId(1l);
        cafeOwner.setCafe(cafeEntity);

        List<OrderEntity> orderEntities = new ArrayList<>();

        OrderEntity order1 = new OrderEntity();
        order1.setOrderTotal(2.0d);
        order1.setStatus(OrderStatus.PENDING);
        UserEntity cutomer1 = new UserEntity();
        cutomer1.setId(2l);
        cutomer1.setName("customer 1");
        order1.setUser(cutomer1);

        List<OrderDetailEntity> orderDetailEntities1 = new ArrayList<>();
        OrderDetailEntity orderDetailEntity11 = new OrderDetailEntity();
        orderDetailEntity11.setQuantity(1);
        CoffeeEntity coffee1 = new CoffeeEntity();
        coffee1.setName("coffee1");
        coffee1.setId(1l);
        orderDetailEntity11.setCoffee(coffee1);
        orderDetailEntities1.add(orderDetailEntity11);

        OrderDetailEntity orderDetailEntity2 = new OrderDetailEntity();
        orderDetailEntity2.setQuantity(1);
        CoffeeEntity  coffee2 = new CoffeeEntity();
        coffee2.setId(2l);
        coffee2.setName("coffee2");
        orderDetailEntity2.setCoffee(coffee2);
        orderDetailEntities1.add(orderDetailEntity2);
        order1.setOrderDetailEntities(orderDetailEntities1);

        orderEntities.add(order1);

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setOrderTotal(1.0d);

        UserEntity customer2 = new UserEntity();
        customer2.setId(2l);
        customer2.setName("customer 2");
        orderEntity2.setUser(customer2);

        List<OrderDetailEntity> orderDetailEntities2 =new ArrayList<>();
        OrderDetailEntity orderDetailEntity21 = new OrderDetailEntity();
        orderDetailEntity21.setQuantity(1);
        orderDetailEntity21.setCoffee(coffee1);
        orderDetailEntities2.add(orderDetailEntity21);

        orderEntity2.setOrderDetailEntities(orderDetailEntities2);
        orderEntities.add(orderEntity2);


        Mockito.when(userTokenService.getUser(token)).thenReturn(cafeOwner);

        List<String> roles = Arrays.asList(Role.CAFE_OWNER.toString());
        Mockito.when(userRoleRepository.findAllRolesByUserId(1l)).thenReturn(roles);

        Mockito.when(orderRepository.findAllByCafeId(1l)).thenReturn(orderEntities);

        List<OrderDto> orderDtos = orderService.getAllOrderCafe(cafeId, token);

        assertEquals(2, orderDtos.size());
        List<OrderDetailDto> orderDetailDtoList = orderDtos.get(0).getOrderDetailDtoList();
        List<OrderDetailDto> orderDetailDtoList1 = orderDtos.get(1).getOrderDetailDtoList();

        assertEquals("customer 1", orderDtos.get(0).getCustomerName());
        assertEquals("customer 2", orderDtos.get(1).getCustomerName());


        assertEquals(2, orderDetailDtoList.size());
        assertEquals(1, orderDetailDtoList1.size());
    }

    @Test
    void updateOrder() {
    }
}