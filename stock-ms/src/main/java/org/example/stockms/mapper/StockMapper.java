package org.example.stockms.mapper;

import org.example.stockms.dto.Stock;
import org.example.stockms.dto.StockRequestDto;
import org.example.stockms.dto.StockResponseDto;
import org.example.stockms.dto.StockUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    // DTO -> Entity
    Stock toStockEntity(StockRequestDto stockRequestDto);

    // Entity -> DTO
    StockResponseDto toStockResponseDto(Stock stock);

    // Para listas: Entity -> DTO
    List<StockResponseDto> toStockResponseDtoList(List<Stock> stocks);
}
