package org.example.pediosms.model.pedido;

import lombok.Data;
import org.example.pediosms.model.detallePedido.DetallePedidoResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDto{
    private Long id;
    private String cliente;
    private LocalDateTime fechaPedido;
    private Boolean estado;
    private List<DetallePedidoResponseDto> detalles;
}
