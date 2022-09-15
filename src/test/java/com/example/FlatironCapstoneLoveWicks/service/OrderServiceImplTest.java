package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.*;
import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import com.example.FlatironCapstoneLoveWicks.model.Candle;
import com.example.FlatironCapstoneLoveWicks.model.CandleOrder;
import com.example.FlatironCapstoneLoveWicks.model.OrderDetails;
import com.example.FlatironCapstoneLoveWicks.repository.CandleRepository;
import com.example.FlatironCapstoneLoveWicks.repository.OrderDetailsRepository;
import com.example.FlatironCapstoneLoveWicks.repository.OrderRepository;
import com.example.FlatironCapstoneLoveWicks.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    CandleRepository candleRepository;
    @Mock
    OrderDetailsRepository orderDetailsRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void getOrderById() {
        Long id = 1L;
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setName("James");
        appUser.setAddress("123 Main St");
        appUser.setEmail("j@email.com");
        appUser.setPhone("4445550000");
        appUser.setPassword(passwordEncoder.encode("pw1234"));

        Candle candle = new Candle();
        candle.setId(1L);
        candle.setName("Name");
        candle.setDescription("Description");
        candle.setPrice(15.00);
        candle.setInStock(true);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(1L);
        orderDTO.setOpen(true);

        CandleOrder candleOrder = new CandleOrder();
        candleOrder.setId(1L);
        candleOrder.setAppUser(appUser);
        candleOrder.setOpen(true);
        candleOrder.getOrderDetails().add(new OrderDetails(1L,candleOrder,candle));

        OrderDetails orderDetails = new OrderDetails(1L,candleOrder,candle);
        OrderDetails orderDetails2 = new OrderDetails(2L,candleOrder,candle);
        List<OrderDetails>orderDetailsList=new ArrayList<>();
        orderDetailsList.add(orderDetails);
        orderDetailsList.add(orderDetails2);

        when(orderRepository.findById(id)).thenReturn(Optional.of(candleOrder));
        when(orderDetailsRepository.findAll().stream().filter(detail->detail.getCandleOrder().getId()==id).toList()).thenReturn(orderDetailsList);
        ReturnOrderDTO returnOrderDTO = new ReturnOrderDTO();
        returnOrderDTO.setOrderId(1L);
        returnOrderDTO.setCustomerId(appUser.getId());
        returnOrderDTO.setOpen(true);
        List<Long>candles=new ArrayList<>();
        candles.add(orderDetails.getCandle().getId());
        candles.add(orderDetails2.getCandle().getId());
        returnOrderDTO.setCandles(candles);

        ReturnOrderDTO actual = orderService.getOrderById(id);
        assertEquals(returnOrderDTO,actual);

    }

    @Test
    void getAllOrders() {
        List<CandleOrder>orders=new ArrayList<>();
        List<GetAllOrdersDTO>getAllOrders=new ArrayList<>();
        when(orderRepository.findAll().stream().toList()).thenReturn(orders);
        List<GetAllOrdersDTO>actual = orderService.getAllOrders();
        assertEquals(getAllOrders,actual);
    }
    @Test
    void deleteOrderById() {
        Long id = 1L;
        willDoNothing().given(orderRepository).deleteById(id);
        orderService.deleteOrderById(id);
        verify(orderRepository, times(1)).deleteById(id);
    }

}