package org.example.compositionpedido.feign;

import org.example.compositionpedido.dto.PedidoRequestDto;
import org.example.compositionpedido.dto.PedidoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="pedidos-ms",url = "http://localhost:8080/api/pedidos")
public interface PedidoClient {
    @PostMapping
    PedidoResponseDto crearPedido(@RequestBody PedidoRequestDto pedidoRequestDto);


}
