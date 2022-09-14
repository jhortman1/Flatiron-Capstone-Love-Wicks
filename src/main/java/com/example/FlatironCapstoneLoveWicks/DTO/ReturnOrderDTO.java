package com.example.FlatironCapstoneLoveWicks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnOrderDTO {
    private Long orderId;
    private Long customerId;
    private Boolean open;
    List<Long>candles;
}
