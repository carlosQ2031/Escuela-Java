package org.example.productoms.mapper;


import org.example.productoms.model.category.Categoria;
import org.example.productoms.model.category.CategoriaRequestDto;
import org.example.productoms.model.category.CategoriaResponseDto;
import org.example.productoms.model.category.CategoriaUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface CategoriaMapper {

    //Categoria <- CategoriaRequestDto
    Categoria toEntity(CategoriaRequestDto categoriaRequestDto);

    //CategoriaResponseDto <- Categoria
    CategoriaResponseDto toCategoriaResponseDto(Categoria categoria);

    //Categoria <- CategoriaUpdateDto
    Categoria toEntity(CategoriaUpdateDto categoriaUpdateDto);

    //List<CategoriaResponseDto> <- List<Categoria>
    List<CategoriaResponseDto> toCategoriaResponseDto(List<Categoria> categorias);
}
