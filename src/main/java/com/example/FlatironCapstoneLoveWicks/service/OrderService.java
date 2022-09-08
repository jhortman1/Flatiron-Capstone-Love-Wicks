package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.model.Candle;
import com.example.FlatironCapstoneLoveWicks.model.Order;
import org.springframework.boot.devtools.autoconfigure.OnEnabledDevToolsCondition;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Order order);
    Order updateOrderById(Long orderId);
    void deleteOrderById(Long orderId);
}
