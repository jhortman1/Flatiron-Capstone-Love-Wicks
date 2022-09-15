package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateCandleDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnCandleDTO;
import com.example.FlatironCapstoneLoveWicks.model.Candle;
import com.example.FlatironCapstoneLoveWicks.repository.CandleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandleServiceImplTest {
    @InjectMocks
    CandleServiceImpl candleService;
    @Mock
    CandleRepository candleRepository;
    @Mock
    ModelMapper modelMapper;

    @Test
    void createCandle() {
        CreateCandleDTO candleDTO = new CreateCandleDTO();
        candleDTO.setName("Name");
        candleDTO.setDescription("Description");
        candleDTO.setPrice(15.00);
        candleDTO.setInStock(true);

        Candle candle = new Candle();
        candle.setName("Name");
        candle.setDescription("Description");
        candle.setPrice(15.00);
        candle.setInStock(true);

        ReturnCandleDTO returnCandleDTO = new ReturnCandleDTO();
        returnCandleDTO.setName("Name");
        returnCandleDTO.setDescription("Description");
        returnCandleDTO.setPrice(15.00);
        returnCandleDTO.setInStock(true);

        when(modelMapper.map(candleDTO, Candle.class)).thenReturn(candle);
        when(candleRepository.save(candle)).thenReturn(candle);
        when(modelMapper.map(candle, ReturnCandleDTO.class)).thenReturn(returnCandleDTO);

        ReturnCandleDTO actual = candleService.createCandle(candleDTO);
        assertEquals(returnCandleDTO,actual);



    }

    @Test
    void updateCandle() {
        Long id = 1L;
        ReturnCandleDTO returnCandleDTO = new ReturnCandleDTO();
        returnCandleDTO.setId(id);
        returnCandleDTO.setName("Name");
        returnCandleDTO.setDescription("Description");
        returnCandleDTO.setPrice(15.00);
        returnCandleDTO.setInStock(true);

        Candle candle = new Candle();
        candle.setId(id);
        candle.setName("Name");
        candle.setDescription("Description");
        candle.setPrice(15.00);
        candle.setInStock(true);

        when(candleRepository.findById(id)).thenReturn(Optional.of(candle));
        when(modelMapper.map(candle,ReturnCandleDTO.class)).thenReturn(returnCandleDTO);
        ReturnCandleDTO actual = candleService.updateCandle(id,returnCandleDTO);
    }

    @Test
    void getCandleById() {
        Long id = 1L;
        ReturnCandleDTO returnCandleDTO = new ReturnCandleDTO();
        Candle candle = new Candle();
        when(candleRepository.findById(id)).thenReturn(Optional.of(candle));
        when(modelMapper.map(candle,ReturnCandleDTO.class)).thenReturn(returnCandleDTO);
        ReturnCandleDTO actual = candleService.getCandleById(id);
        assertEquals(returnCandleDTO,actual);
    }

    @Test
    void deleteCandleById() {
        Long id = 1L;
        Candle candle = new Candle();
        ReturnCandleDTO returnCandleDTO = new ReturnCandleDTO();
        when(candleRepository.findById(id)).thenReturn(Optional.of(candle));
        when(candleRepository.save(candle)).thenReturn(candle);
        when(modelMapper.map(candle, ReturnCandleDTO.class)).thenReturn(returnCandleDTO);
        ReturnCandleDTO actual = candleService.deleteCandleById(id);
        assertEquals(returnCandleDTO,actual);

    }
}