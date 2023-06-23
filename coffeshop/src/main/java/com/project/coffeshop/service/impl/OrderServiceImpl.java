package com.project.coffeshop.service.impl;

import com.project.coffeshop.repo.OrderRepository;
import com.project.coffeshop.service.OrderService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }
}
