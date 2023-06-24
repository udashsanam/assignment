package com.project.coffeshop.pojo.request;

import lombok.Data;

import java.util.HashMap;

@Data
public class OrderPojo {

    private HashMap<Long, Integer> coffeeQuantityMap;

    private HashMap<Long, String> coffeeInstruction;

    private Long cafeId;
}
