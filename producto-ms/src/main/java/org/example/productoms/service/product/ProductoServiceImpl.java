package org.example.productoms.service.product;

import lombok.AllArgsConstructor;
import org.example.productoms.model.category.Categoria;
import org.example.productoms.model.product.Producto;
import org.example.productoms.model.product.ProductoUpdateRequestDto;
import org.example.productoms.repository.CategoriaRepository;
import org.example.productoms.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("El producto no puede ser nulo");

        // Validar que exista la categoría
        Long categoriaId = producto.getCategoria() != null ? producto.getCategoria().getId() : null;
        if (categoriaId == null) {
            throw new IllegalStateException("El producto debe tener una categoría válida (id no puede ser null)");
        }

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalStateException("Categoría no encontrada con id " + categoriaId));

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));
    }

    @Override
    public Producto updateById(Long id, Producto producto) {
        if (id == null || producto == null)
            throw new IllegalArgumentException("El id y el producto no pueden ser nulos");

        Producto productoExistente = findById(id);
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());

        return productoRepository.save(productoExistente);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }

    @Override
    public Producto patchById(Long id, ProductoUpdateRequestDto dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("El id y el DTO no pueden ser nulos");

        Producto productoExistente = findById(id);

        // Actualiza campos si existen
        if (dto.getNombre() != null) productoExistente.setNombre(dto.getNombre());
        if (dto.getPrecio() != null) productoExistente.setPrecio(dto.getPrecio());

        // Actualiza categoría si existe
        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Categoría no encontrada con id " + dto.getCategoriaId()));
            productoExistente.setCategoria(categoria);
        }

        return productoRepository.save(productoExistente);
    }

}
