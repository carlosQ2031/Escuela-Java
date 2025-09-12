package org.example.compositionpedido.controller;

import org.example.compositionpedido.dto.PedidoCompositionDto;
import org.example.compositionpedido.dto.PedidoRequestDto;
import org.example.compositionpedido.service.PedidoCompositionService;
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
    public PedidoCompositionDto crearOrder(@RequestBody PedidoRequestDto pedidoRequest) {
        return compositionService.crearPedido(pedidoRequest);
    }

    @GetMapping("/pedidos")
    public List<PedidoCompositionDto> obtenerTodosPedidos() {
        return compositionService.obtenerTodosPedidos();
    }
}
