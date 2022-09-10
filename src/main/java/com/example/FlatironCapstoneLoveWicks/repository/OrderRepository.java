package com.example.FlatironCapstoneLoveWicks.repository;

import com.example.FlatironCapstoneLoveWicks.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
