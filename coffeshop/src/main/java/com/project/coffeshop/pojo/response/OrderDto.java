package com.project.coffeshop.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.coffeshop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Long orderId;

    private OrderStatus orderStatus;

    private Double orderTotal;

    private HashMap<Long, String> coffeeName;

    private HashMap<Long, Integer> coffeeQuantity;

    public OrderDto(Long orderId, OrderStatus orderStatus, Double orderTotal){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }
}
