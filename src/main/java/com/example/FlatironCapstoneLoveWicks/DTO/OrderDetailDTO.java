package com.example.FlatironCapstoneLoveWicks.DTO;

import com.example.FlatironCapstoneLoveWicks.model.Candle;
import com.example.FlatironCapstoneLoveWicks.model.CandleOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private CandleOrder candleOrder;
    private Candle candle;
}
