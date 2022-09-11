package com.example.FlatironCapstoneLoveWicks.repository;

import com.example.FlatironCapstoneLoveWicks.model.CandleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CandleOrder, Long> {
}
