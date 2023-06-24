package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.OrderPojo;
import com.project.coffeshop.pojo.request.UpdateOrderPojo;
import com.project.coffeshop.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController extends BaseController{

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderPojo orderPojo, HttpServletRequest request){
        String token = getAccessToken(request);
        return ResponseEntity.ok(successResponse("Successfully place order", orderService.create(orderPojo, token)));
    }

    @GetMapping("get-all-orders")
    public ResponseEntity<?> getAllOrders(HttpServletRequest request){
        String token = getAccessToken(request);
        return ResponseEntity.ok(successResponse("Successfully fetched", orderService.getAllOrders(token)));
    }

    @GetMapping("get-all-orders-coffee-shop")
    public ResponseEntity<?> getAllOrdersRestaurant(@RequestParam("cafeId") Long cafeId, HttpServletRequest request){
        String token = getAccessToken(request);
        return ResponseEntity.ok(successResponse("Successfully fetched", orderService.getAllOrderCafe(cafeId, token)));
    }

    @PutMapping("update-order")
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderPojo orderPojo, HttpServletRequest request){
        String token = getAccessToken(request);
        return ResponseEntity.ok(successResponse("update successfully", orderService.updateOrder(orderPojo, token)));
    }



}
