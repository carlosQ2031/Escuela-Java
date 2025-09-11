package org.example.productoms.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.example.productoms.mapper.ProductoMapper;
import org.example.productoms.model.product.*;
import org.example.productoms.service.product.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
@Validated
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    @PostMapping
    public ResponseEntity<ProductoSaveResponseDto> save(
            @Valid @RequestBody @NotNull ProductoSaveRequestDto dto) {

        Producto producto = productoMapper.toEntity(dto);
        Producto savedProducto = productoService.save(producto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoMapper.toProductoSaveResponseDto(savedProducto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getAllProductos() {
        List<Producto> productos = productoService.listAll();
        return ResponseEntity.ok(productoMapper.map(productos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(productoMapper.map(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoUpdateRequestDto dto) {

        Producto updatedProducto = productoService.updateById(id, productoMapper.toEntity(dto));
        return ResponseEntity.ok(productoMapper.map(updatedProducto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDto> patchProducto(
            @PathVariable Long id,
            @RequestBody ProductoUpdateRequestDto dto) {

        Producto updatedProducto = productoService.patchById(id, dto);
        return ResponseEntity.ok(productoMapper.map(updatedProducto));
    }

}
