package org.example.compositionpedido.controller;

import org.example.compositionpedido.dto.PedidoCompositionDto;
import org.example.compositionpedido.dto.PedidoRequestDto;
import org.example.compositionpedido.service.PedidoCompositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/composition/pedido")
public class CompositionPedidoController {

    private final PedidoCompositionService compositionService;

    public CompositionPedidoController(PedidoCompositionService pedidoCompositionService) {
        this.compositionService = pedidoCompositionService;
    }

    @PostMapping
    public ResponseEntity<PedidoCompositionDto> crearOrder(@RequestBody PedidoRequestDto pedidoRequest) {
        PedidoCompositionDto pedido = compositionService.crearPedido(pedidoRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedido);
    }


}
