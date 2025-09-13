package org.example.stockms.controller;

import org.example.stockms.dto.Stock;
import org.example.stockms.dto.StockRequestDto;
import org.example.stockms.dto.StockResponseDto;
import org.example.stockms.exception.ExceptionPersonalizada;
import org.example.stockms.mapper.StockMapper;
import org.example.stockms.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class StockControllerTest {
    @Mock
    private StockService stockService;

    @Mock
    private StockMapper stockMapper;

    @InjectMocks
    private StockController stockController;

    private Stock stock;
    private StockResponseDto stockResponseDto;
    private StockRequestDto stockRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        stock = new Stock();
        stock.setId(1);
        stock.setProductId(100);
        stock.setWhareHouse(1);
        stock.setQuantity(50);

        stockRequestDto = new StockRequestDto();
        stockRequestDto.setProductId(100);
        stockRequestDto.setWhareHouse(1);
        stockRequestDto.setQuantity(50);

        stockResponseDto = new StockResponseDto();
        stockResponseDto.setId(1);
        stockResponseDto.setProductId(100);
        stockResponseDto.setWhareHouse(1);
        stockResponseDto.setQuantity(50);
    }

    @Test
    void guardarStock() {
        when(stockMapper.toStockEntity(stockRequestDto)).thenReturn(stock);
        when(stockService.guardarStock(stock)).thenReturn(stock);
        when(stockMapper.toStockResponseDto(stock)).thenReturn(stockResponseDto);

        ResponseEntity<StockResponseDto> response = stockController.guardarStock(stockRequestDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(stockResponseDto, response.getBody());
    }

    @Test
    void obtenerStocks() {
        List<Stock> listaStocks = Arrays.asList(stock);
        List<StockResponseDto> listaResponse = Arrays.asList(stockResponseDto);

        when(stockService.obtenerStocks()).thenReturn(listaStocks);
        when(stockMapper.toStockResponseDtoList(listaStocks)).thenReturn(listaResponse);

        ResponseEntity<List<StockResponseDto>> response = stockController.obtenerStocks();

        assertEquals(1, response.getBody().size());
        assertEquals(stockResponseDto, response.getBody().get(0));
    }

    @Test
    void obtenerStockPorId() {
        when(stockService.obtenerStockPorId(1)).thenReturn(stock);
        when(stockMapper.toStockResponseDto(stock)).thenReturn(stockResponseDto);

        ResponseEntity<StockResponseDto> response = stockController.obtenerStockPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(stockResponseDto, response.getBody());
    }

    @Test
    void obtenerStockPorIdExceprion() {
        when(stockService.obtenerStockPorId(2)).thenThrow(new ExceptionPersonalizada("No se encontró el stock con el id: 2"));

        ExceptionPersonalizada ex = assertThrows(ExceptionPersonalizada.class,
                () -> stockController.obtenerStockPorId(2));

        assertEquals("No se encontró el stock con el id: 2", ex.getMessage());
    }
}
