package com.example.javabucks.controller;

import com.example.javabucks.model.Order;
import com.example.javabucks.service.OrderService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @QueryMapping
    public List<Order> findAllOrders() {
        return orderService.findAll();
    }
}
