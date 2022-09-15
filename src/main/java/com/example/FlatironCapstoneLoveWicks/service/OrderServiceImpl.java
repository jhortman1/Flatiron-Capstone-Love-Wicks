package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.*;
import com.example.FlatironCapstoneLoveWicks.model.*;
import com.example.FlatironCapstoneLoveWicks.repository.CandleRepository;
import com.example.FlatironCapstoneLoveWicks.repository.OrderDetailsRepository;
import com.example.FlatironCapstoneLoveWicks.repository.OrderRepository;
import com.example.FlatironCapstoneLoveWicks.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    CandleRepository candleRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public ReturnOrderDTO createOrder(OrderDTO orderDTO) {
        log.info("Saving Order to Database for Customer {}",orderDTO.getCustomerId());
        AppUser user = userRepository.findById(orderDTO.getCustomerId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        CandleOrder newCandleOrder = modelMapper.map(new CreateOrderDTO(user,true), CandleOrder.class);
        orderRepository.save(newCandleOrder);
        user.getCandleOrders().add(newCandleOrder);
        orderDTO.getCandles().stream().forEach(id ->{
            Optional<Candle> candle = candleRepository.findById(id);
            OrderDetails od = modelMapper.map(new OrderDetailDTO(newCandleOrder,candle.get()),OrderDetails.class);
            orderDetailsRepository.save(od);
            newCandleOrder.getOrderDetails().add(od);
            candle.get().getOrderDetails().add(od);
        });
        ReturnOrderDTO returnOrderDTO = new ReturnOrderDTO(newCandleOrder.getId(), user.getId(), newCandleOrder.getOpen(),orderDTO.getCandles());
        return returnOrderDTO;
    }

    @Override
    public ReturnOrderDTO getOrderById(Long orderId) {
        log.info("Getting Order {} from Database",orderId);
        CandleOrder candleOrder = orderRepository.findById(orderId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll().stream().filter(detail->detail.getCandleOrder().getId()==orderId).toList();
        List<Long>candleIds=new ArrayList<>();
        for (OrderDetails detail : orderDetails)
        {
               candleIds.add(detail.getCandle().getId());
        }
        return new ReturnOrderDTO(candleOrder.getId(), candleOrder.getAppUser().getId(), candleOrder.getOpen(),candleIds);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<GetAllOrdersDTO> getAllOrders() {
        List<CandleOrder>orders = orderRepository.findAll().stream().toList();
        List<GetAllOrdersDTO>getAllOrders=new ArrayList<>();
        for (CandleOrder candleOrder:orders) {
            getAllOrders.add(new GetAllOrdersDTO(candleOrder.getId(),candleOrder.getOpen()));
        }
        return getAllOrders;
    }
}
