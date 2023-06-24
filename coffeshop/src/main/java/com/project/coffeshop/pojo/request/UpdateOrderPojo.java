package com.project.coffeshop.pojo.request;

import lombok.Data;

@Data
public class UpdateOrderPojo {
    private Long orderId;

    private String orderStatus;
}
