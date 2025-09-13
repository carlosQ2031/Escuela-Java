package org.example.stockms.respository;

import org.example.stockms.dto.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {
}
