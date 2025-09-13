package org.example.stockms.service;

import lombok.AllArgsConstructor;
import org.example.stockms.dto.Stock;
import org.example.stockms.exception.ExceptionPersonalizada;
import org.example.stockms.respository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StockServiceImpl implements StockService{
    private final StockRepository stockRepository;

    @Override
    public Stock guardarStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> obtenerStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock obtenerStockPorId(Integer id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new ExceptionPersonalizada("No se encontró el stock con el id: " + id));
    }


}
