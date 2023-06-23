package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.OrderDetailEntity;
import com.project.coffeshop.entity.OrderEntity;
import com.project.coffeshop.repo.OrderDetailRepository;
import com.project.coffeshop.service.OrderDetailService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailEntity, Long> implements OrderDetailService {


    private final OrderDetailRepository orderDetailRepository;


    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        super(orderDetailRepository);
        this.orderDetailRepository = orderDetailRepository;
    }
}
