package org.example.pediosms.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.example.pediosms.mapper.PedidoMapper;
import org.example.pediosms.model.PedidoRequestDto;
import org.example.pediosms.model.PedidoResponseDto;
import org.example.pediosms.model.PedidoUpdateDto;
import org.example.pediosms.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> getAll() {
        return ResponseEntity.ok(pedidoMapper.toListResponseDto(pedidoService.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> getById(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(pedidoMapper.toResponseDto(pedidoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> create(@Valid @RequestBody @NotNull PedidoRequestDto pedidoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoMapper.toResponseDto(pedidoService.save(pedidoMapper.toEntity(pedidoRequestDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> update(@PathVariable @NotNull @Min(1) Long id,
                                                    @Valid @RequestBody PedidoUpdateDto pedidoUpdateDto) {
        return ResponseEntity.ok(pedidoMapper.toResponseDto(pedidoService.updateById(id, pedidoMapper.toEntity(pedidoUpdateDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Min(1) Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
