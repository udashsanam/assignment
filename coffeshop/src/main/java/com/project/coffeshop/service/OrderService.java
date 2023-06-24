package com.project.coffeshop.service;

import com.project.coffeshop.entity.OrderEntity;
import com.project.coffeshop.pojo.request.OrderPojo;
import com.project.coffeshop.pojo.response.OrderDto;

import java.util.List;

public interface OrderService extends BaseService<OrderEntity, Long> {


    OrderDto create(OrderPojo orderPojo, String token);

    List<OrderDto> getAllOrders(String token);
}
