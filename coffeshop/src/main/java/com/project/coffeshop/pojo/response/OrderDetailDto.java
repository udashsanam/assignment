package com.project.coffeshop.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private Long coffeeId;

    private String coffeeName;

    private Integer quantity;

}
