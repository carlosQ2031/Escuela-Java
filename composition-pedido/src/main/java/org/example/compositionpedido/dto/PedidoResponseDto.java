package org.example.compositionpedido.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDto {
    private Long id;
    private String cliente;
    private LocalDateTime fechaPedido;
    private Boolean estado;
    private List<DetallePedidoResponseDto> detalles;
}
