package com.example.FlatironCapstoneLoveWicks.Controller;

import com.example.FlatironCapstoneLoveWicks.DTO.ReturnOrderDTO;
import com.example.FlatironCapstoneLoveWicks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/orders")
    public ResponseEntity<List<ReturnOrderDTO>>getAllOrders()
    {

    }
}
