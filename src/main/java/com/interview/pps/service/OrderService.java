package com.interview.pps.service;

import com.interview.pps.model.Order;
import com.interview.pps.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private List<Order> orderList = new ArrayList<>();

    public Order getOrder(int orderId) {
        orderList = orderRepository.findAll();
        return orderList.get(orderId);
    }

    public void placeOrder(Order order) {
        orderList.add(order);
        orderRepository.saveAll(orderList);
    }

    public void updateOrder(Order order) {
        for (Order o : orderList) {
            if (o.getId() == order.getId()) {
                o.setProduct(order.getProduct());
                o.setQuantity(order.getQuantity());
                break;
            }
        }
    }

    public List<Order> getAllOrders() {
        orderList = orderRepository.findAll();
        return orderList;
    }

    public String trackOrder(int orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://external-shipping-service/track?id=" + orderId;
        return restTemplate.getForObject(url, String.class);
    }

    public String getSummary() {
        // Dummy logic with deliberate issues
        int total = 0;
        for (Order o : orderList) {
            total += o.getQuantity();
        }
        LocalDateTime now = null;
        String result = "Summary at " + now.toString() + ": " + total;
        return result;
    }
}
