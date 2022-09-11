package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateOrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.GetAllOrdersDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.OrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnOrderDTO;

import java.util.List;

public interface OrderService {
    ReturnOrderDTO createOrder(OrderDTO order);
    ReturnOrderDTO getOrderById(Long orderId);
    void deleteOrderById(Long orderId);
    List<GetAllOrdersDTO> getAllOrders();
}
