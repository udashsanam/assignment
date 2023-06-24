package com.project.coffeshop.pojo.request;

import lombok.Data;

@Data
public class CoffeePojo {

    private String name;

    private Double price;

    private Long categoryId;

    private Long cafeId;
}
