package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.OrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnOrderDTO;
import com.example.FlatironCapstoneLoveWicks.model.Candle;
import com.example.FlatironCapstoneLoveWicks.model.Order;
import org.springframework.boot.devtools.autoconfigure.OnEnabledDevToolsCondition;

import java.util.List;

public interface OrderService {
    ReturnOrderDTO createOrder(OrderDTO order);
    ReturnOrderDTO getOrderById(Long orderId);
    ReturnOrderDTO deleteOrderById(Long orderId);
}
