package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateCandleDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnCandleDTO;
import com.example.FlatironCapstoneLoveWicks.model.Candle;

import java.util.List;

public interface CandleService {
    ReturnCandleDTO createCandle(CreateCandleDTO candleDTO);
    ReturnCandleDTO updateCandle(Long id, ReturnCandleDTO candle);
    ReturnCandleDTO getCandleById (Long candleId);
    ReturnCandleDTO deleteCandleById(Long candleId);
    List<ReturnCandleDTO> getAllCandles();
}
