package org.example.productoms.service.product;

import org.example.productoms.model.product.Producto;
import org.example.productoms.model.product.ProductoUpdateRequestDto;

import java.util.List;

public interface ProductoService {
    // Crear
    Producto save(Producto producto);

    // Obtener todos
    List<Producto> listAll();

    // Obtener por ID
    Producto findById(Long id);

    // Actualizar por ID
    Producto updateById(Long id, Producto producto);

    // Eliminar por ID
    void deleteById(Long id);

    Producto patchById(Long id, ProductoUpdateRequestDto dto);

}
