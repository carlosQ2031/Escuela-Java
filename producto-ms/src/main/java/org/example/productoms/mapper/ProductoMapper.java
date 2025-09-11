package org.example.productoms.mapper;


import org.example.productoms.model.product.ProductoDto;
import org.example.productoms.model.product.ProductoSaveRequestDto;
import org.example.productoms.model.product.ProductoSaveResponseDto;
import org.example.productoms.model.product.ProductoUpdateRequestDto;
import org.example.productoms.model.product.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    ProductoDto map(Producto producto);
    @Mapping(source = "categoriaId", target = "categoria.id")
    Producto toEntity(ProductoSaveRequestDto productoSaveRequestDto);


    List<ProductoDto> map(List<Producto> productos);

    void updateUserFromDto(ProductoUpdateRequestDto dto, @MappingTarget Producto producto);

    ProductoSaveResponseDto toProductoSaveResponseDto(Producto producto);

    default Producto toEntity(ProductoUpdateRequestDto dto) {
        Producto producto = new Producto();
        updateUserFromDto(dto, producto);
        return producto;
    }
}