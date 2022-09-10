package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateCandleDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnCandleDTO;
import com.example.FlatironCapstoneLoveWicks.model.Candle;
import com.example.FlatironCapstoneLoveWicks.repository.CandleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CandleServiceImpl implements CandleService{
    @Autowired
    CandleRepository candleRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public ReturnCandleDTO createCandle(CreateCandleDTO candleDTO) {
        Candle newCandle = modelMapper.map(candleDTO,Candle.class);
        candleRepository.save(newCandle);
        return modelMapper.map(newCandle,ReturnCandleDTO.class);
    }

    @Override
    public ReturnCandleDTO updateCandle(ReturnCandleDTO candleDTO) {
        Candle updateCandle = candleRepository.findById(candleDTO.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCandle.setDescription(candleDTO.getDescription());
        updateCandle.setName(candleDTO.getName());
        updateCandle.setPrice(candleDTO.getPrice());
        updateCandle.setPhotoId(candleDTO.getPhotoId());
        updateCandle.setInStock(candleDTO.isInStock());
        return modelMapper.map(updateCandle, ReturnCandleDTO.class);
    }

    @Override
    public ReturnCandleDTO getCandleById(Long candleId) {
        return modelMapper.
                map(candleRepository.findById(candleId)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)),
                ReturnCandleDTO.class);
    }

    @Override
    public ReturnCandleDTO deleteCandleById(Long candleId) {
        Candle deletedCandle = candleRepository.findById(candleId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        deletedCandle.setInStock(false);
        return modelMapper.map(deletedCandle, ReturnCandleDTO.class);
    }

    @Override
    public List<ReturnCandleDTO> getAllCandles() {
        return candleRepository.findAll().stream().map(candle -> modelMapper.map(candle, ReturnCandleDTO.class)).toList();
    }
}
