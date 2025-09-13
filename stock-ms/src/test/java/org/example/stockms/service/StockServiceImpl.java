package org.example.stockms.service;

import org.example.stockms.dto.Stock;
import org.example.stockms.exception.ExceptionPersonalizada;
import org.example.stockms.respository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService; // <--- inyecta la implementación real

    private Stock stock1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stock1 = new Stock();
        stock1.setId(1);
        stock1.setProductId(100);
        stock1.setWhareHouse(1);
        stock1.setQuantity(50);
    }

    @Test
    void guardarStock() {
        when(stockRepository.save(stock1)).thenReturn(stock1);

        Stock resultado = stockService.guardarStock(stock1);

        assertEquals(stock1, resultado);
        verify(stockRepository, times(1)).save(stock1);
    }

    @Test
    void obtenerStocks() {
        Stock stock2 = new Stock();
        stock2.setId(2);
        stock2.setProductId(101);
        stock2.setWhareHouse(2);
        stock2.setQuantity(30);

        List<Stock> lista = Arrays.asList(stock1, stock2);
        when(stockRepository.findAll()).thenReturn(lista);

        List<Stock> resultado = stockService.obtenerStocks();

        assertEquals(2, resultado.size());
        assertEquals(stock1, resultado.get(0));
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void obtenerStockPorId() {
        when(stockRepository.findById(1)).thenReturn(Optional.of(stock1));

        Stock resultado = stockService.obtenerStockPorId(1);

        assertEquals(stock1, resultado);
        verify(stockRepository, times(1)).findById(1);
    }

    @Test
    void obtenerStockPorIdExceprion() {
        when(stockRepository.findById(2)).thenReturn(Optional.empty());

        ExceptionPersonalizada ex = assertThrows(ExceptionPersonalizada.class,
                () -> stockService.obtenerStockPorId(2));

        assertEquals("No se encontró el stock con el id: 2", ex.getMessage());
        verify(stockRepository, times(1)).findById(2);
    }
}
