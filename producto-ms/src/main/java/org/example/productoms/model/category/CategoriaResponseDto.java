package org.example.productoms.model.category;

import org.example.productoms.model.product.ProductoDto;

import java.util.List;

public record CategoriaResponseDto(Long id, String nombre, Boolean active, List<ProductoDto> productos) {
}
