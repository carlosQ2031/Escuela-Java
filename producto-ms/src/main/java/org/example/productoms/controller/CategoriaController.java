package org.example.productoms.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.example.productoms.model.category.CategoriaRequestDto;
import org.example.productoms.model.category.CategoriaResponseDto;
import org.example.productoms.model.category.CategoriaUpdateDto;
import org.example.productoms.service.categoria.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
@Validated
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> guardarCategoria(
            @Valid @RequestBody @NotNull CategoriaRequestDto categoriaRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.save(categoriaRequestDto));
    }

    // GET /api/categorias → todas (active true y false)
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> obtenerTodasCategorias() {
        return ResponseEntity.ok(categoriaService.listAllComplete());
    }

    // GET /api/categorias/active → solo activas
    @GetMapping("/active")
    public ResponseEntity<List<CategoriaResponseDto>> obtenerCategoriasActivas() {
        return ResponseEntity.ok(categoriaService.listAll());
    }

    // GET /api/categorias/inactive → solo inactivas
    @GetMapping("/inactive")
    public ResponseEntity<List<CategoriaResponseDto>> obtenerCategoriasInactivas() {
        return ResponseEntity.ok(categoriaService.listAllInactive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> obtenerCategoriaPorId(
            @PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> actualizarCategoria(
            @PathVariable @NotNull @Min(1) Long id,
            @Valid @RequestBody CategoriaUpdateDto categoriaUpdateDto) {
        return ResponseEntity.ok(categoriaService.updateById(id, categoriaUpdateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> corregirCategoria(
            @PathVariable @NotNull @Min(1) Long id,
            @Valid @RequestBody CategoriaUpdateDto categoriaUpdateDto) {
        return ResponseEntity.ok(categoriaService.updatePatch(id, categoriaUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable @NotNull @Min(1) Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}