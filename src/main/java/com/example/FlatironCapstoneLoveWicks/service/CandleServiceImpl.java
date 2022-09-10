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
        log.info("Saving Candle {} to Database",candleDTO.getName());
        Candle newCandle = modelMapper.map(candleDTO,Candle.class);
        candleRepository.save(newCandle);
        return modelMapper.map(newCandle,ReturnCandleDTO.class);
    }

    @Override
    public ReturnCandleDTO updateCandle(Long id, ReturnCandleDTO candleDTO) {
        log.info("Updating Candle id {} Candle name {} in Database",candleDTO.getId(),candleDTO.getName());
        Candle updateCandle = candleRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCandle.setDescription(candleDTO.getDescription());
        updateCandle.setName(candleDTO.getName());
        updateCandle.setPrice(candleDTO.getPrice());
        updateCandle.setPhotoId(candleDTO.getPhotoId());
        updateCandle.setInStock(candleDTO.isInStock());
        return modelMapper.map(updateCandle, ReturnCandleDTO.class);
    }

    @Override
    public ReturnCandleDTO getCandleById(Long candleId) {
        log.info("Getting Candle id {} from Database",candleId);
        return modelMapper.
                map(candleRepository.findById(candleId)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)),
                ReturnCandleDTO.class);
    }

    @Override
    public ReturnCandleDTO deleteCandleById(Long candleId) {
        log.info("Changing Candle Id {} to out of stock in Database",candleId);
        Candle deletedCandle = candleRepository.findById(candleId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        deletedCandle.setInStock(false);
        return modelMapper.map(deletedCandle, ReturnCandleDTO.class);
    }

    @Override
    public List<ReturnCandleDTO> getAllCandles() {
        log.info("Getting all candles from Database");
        return candleRepository.findAll().stream().map(candle -> modelMapper.map(candle, ReturnCandleDTO.class)).toList();
    }
}
