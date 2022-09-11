package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.OrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnOrderDTO;
import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import com.example.FlatironCapstoneLoveWicks.model.Order;
import com.example.FlatironCapstoneLoveWicks.repository.OrderDetailsRepository;
import com.example.FlatironCapstoneLoveWicks.repository.OrderRepository;
import com.example.FlatironCapstoneLoveWicks.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public ReturnOrderDTO createOrder(OrderDTO orderDTO) {
        log.info("Saving Order to Database for Customer {}",orderDTO.getCustomerId());
        AppUser user = userRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Order newOrder = modelMapper.map(orderDTO,Order.class);
        orderRepository.save(newOrder);
        user.getOrders().add(newOrder);
        return modelMapper.map(newOrder,ReturnOrderDTO.class);
    }

    @Override
    public ReturnOrderDTO getOrderById(Long orderId) {
        log.info("Getting Order {} from Database",orderId);
        return modelMapper.map(
                orderRepository.findById(orderId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)),
                ReturnOrderDTO.class);
    }

    @Override
    public ReturnOrderDTO deleteOrderById(Long orderId) {
        Order deleteOrder = orderRepository.findById(orderId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        orderRepository.delete(deleteOrder);
        return null;
    }
}
