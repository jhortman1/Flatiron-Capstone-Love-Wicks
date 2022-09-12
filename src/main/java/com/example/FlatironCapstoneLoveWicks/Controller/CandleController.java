package com.example.FlatironCapstoneLoveWicks.Controller;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateCandleDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.DeleteOrderDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnCandleDTO;
import com.example.FlatironCapstoneLoveWicks.service.CandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
public class CandleController {
    @Autowired
    CandleService candleService;

    private byte[] photoId;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/candles")
    public ResponseEntity<List<ReturnCandleDTO>>getCandles()
    {
        return ResponseEntity.ok().body(candleService.getAllCandles());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/candle/{id}")
    ResponseEntity<ReturnCandleDTO>getUserById(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(candleService.getCandleById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/candle")
    public ResponseEntity<ReturnCandleDTO> createCandle(@RequestBody CreateCandleDTO candleDTO)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/candle").toUriString());
        candleDTO.setPhotoId(photoId);
        this.photoId = null;
        return ResponseEntity.created(uri).body(candleService.createCandle(candleDTO));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/candle/{id}")
    public ResponseEntity<ReturnCandleDTO> updateCandleById(@PathVariable("id") Long id, @RequestBody ReturnCandleDTO candleDTO)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/candle/{id}").toUriString());
        return ResponseEntity.created(uri).body(candleService.updateCandle(id, candleDTO));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/candle/{id}")
    public ResponseEntity<ReturnCandleDTO> deleteCandle(@PathVariable("id") Long id)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/candle/{id}").toUriString());
        return ResponseEntity.created(uri).body(candleService.deleteCandleById(id));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        this.photoId = file.getBytes();
    }
}
