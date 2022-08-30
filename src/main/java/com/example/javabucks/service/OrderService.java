package com.example.javabucks.service;

import com.example.javabucks.model.Coffee;
import com.example.javabucks.model.Order;
import com.example.javabucks.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {

    private final CoffeeService coffeeService;
    private final UserService userService;

    private List<Order> orders = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public OrderService(CoffeeService coffeeService, UserService userService) {
        this.coffeeService = coffeeService;
        this.userService = userService;
    }


    public List<Order> findAll() {
        return orders;
    }

    public List<Order> findAllByUsername(String username) {
        return orders.stream().filter(order -> order.user().username().equals(username)).toList();
    }

    public Optional<Order> findOne(Integer id) {
        return orders.stream().filter(order -> order.orderId() == id).findFirst();
    }

    public Order create(Coffee coffee, User userId) {
        Order order = new Order(id.incrementAndGet(),coffee, userId, OffsetDateTime.now());
        orders.add(order);
        return order;
    }

    @PostConstruct
    private void init() {
        // create a few orders
        create(coffeeService.findOne(1).get(),userService.findOne(1).get());
        create(coffeeService.findOne(2).get(),userService.findOne(2).get());
        create(coffeeService.findOne(2).get(),userService.findOne(2).get());
    }

}
