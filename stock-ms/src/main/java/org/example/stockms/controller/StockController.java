package org.example.stockms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.stockms.dto.Stock;
import org.example.stockms.dto.StockRequestDto;
import org.example.stockms.dto.StockResponseDto;
import org.example.stockms.exception.ExceptionPersonalizada;
import org.example.stockms.mapper.StockMapper;
import org.example.stockms.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockMapper stockMapper;
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponseDto> guardarStock(@Valid @RequestBody StockRequestDto stockRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockMapper.toStockResponseDto(stockService.guardarStock(stockMapper.toStockEntity(stockRequestDto))));
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDto>> obtenerStocks() {
        return ResponseEntity.ok(stockMapper.toStockResponseDtoList(stockService.obtenerStocks()));
    }

    //GetMapping con exceptionNoEncontradaPersonalizada
    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDto> obtenerStockPorId(@PathVariable Integer id){
        return ResponseEntity.ok(stockMapper.toStockResponseDto(stockService.obtenerStockPorId(id)));
    }
}
