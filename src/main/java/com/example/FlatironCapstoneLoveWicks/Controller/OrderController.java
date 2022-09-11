package com.example.FlatironCapstoneLoveWicks.Controller;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateOrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.DeleteOrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.OrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnOrderDTO;
import com.example.FlatironCapstoneLoveWicks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/order")
    public ResponseEntity<ReturnOrderDTO>createOrder(@RequestBody OrderDTO orderDTO)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString());
        return ResponseEntity.created(uri).body(orderService.createOrder(orderDTO));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("order/{id}")
    public  ResponseEntity<ReturnOrderDTO>getOrderById(@PathVariable("id") Long orderId)
    {
        return ResponseEntity.ok().body(orderService.getOrderById(orderId));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("order/{id}")
    public void deleteOrder(@PathVariable("id")Long id)
    {
        orderService.deleteOrderById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("orders")
    public ResponseEntity<List<CreateOrderDTO>> getAllOrders()
    {
        return  ResponseEntity.ok().body(orderService.getAllOrders());
    }


}
