package org.example.compositionpedido.feign;

import org.example.compositionpedido.dto.PedidoCompositionDto;
import org.example.compositionpedido.dto.PedidoRequestDto;
import org.example.compositionpedido.dto.PedidoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="pedidos-ms")
public interface PedidoClient {
    @PostMapping("/api/pedidos")
    PedidoResponseDto crearPedido(@RequestBody PedidoRequestDto pedidoRequestDto);

    @GetMapping("/api/pedidos")
    List<PedidoResponseDto> obtenerTodosPedidos();

}
