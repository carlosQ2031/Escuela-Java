package org.example.productoms.service.categoria;



import org.example.productoms.model.category.CategoriaRequestDto;
import org.example.productoms.model.category.CategoriaResponseDto;
import org.example.productoms.model.category.CategoriaUpdateDto;

import java.util.List;

public interface CategoriaService {
    // Crear una nueva categoría
    CategoriaResponseDto save(CategoriaRequestDto categoriaRequestDto);

    // Obtener todas las categorías activas
    List<CategoriaResponseDto> listAll();

    // Obtener todas las categorías inactivas
    List<CategoriaResponseDto> listAllInactive();

    // Obtener todas las categorías, activas e inactivas
    List<CategoriaResponseDto> listAllComplete();

    // Obtener categoría por ID
    CategoriaResponseDto findById(Long id);

    // Actualizar categoría por ID (PUT completo)
    CategoriaResponseDto updateById(Long id, CategoriaUpdateDto categoriaUpdateDto);

    // Actualización parcial de categoría por ID (PATCH)
    CategoriaResponseDto updatePatch(Long id, CategoriaUpdateDto categoriaUpdateDto);

    // "Eliminar" categoría: soft delete (active = false) y desvincular productos
    void deleteById(Long id);
}
