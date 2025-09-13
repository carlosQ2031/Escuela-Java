package org.example.stockms.service;

import org.example.stockms.dto.Stock;

import java.util.List;

public interface StockService {
    //Guardar
    Stock guardarStock(Stock stock);

    //Obtener todos
    List<Stock> obtenerStocks();

    //Obtener 1
    Stock obtenerStockPorId(Integer id);
}
