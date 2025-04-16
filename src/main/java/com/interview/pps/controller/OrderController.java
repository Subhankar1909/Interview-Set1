package com.interview.pps.controller;

import com.interview.pps.model.Order;
import com.interview.pps.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable("orderId") int orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return "Order placed";
    }

    @PutMapping("/update")
    public void updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @GetMapping("/list")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/track/{orderId}")
    public String trackOrder(@PathVariable int orderId) {
        return orderService.trackOrder(orderId);
    }

    @GetMapping("/summary")
    public String getSummary() {
        return orderService.getSummary();
    }
}
