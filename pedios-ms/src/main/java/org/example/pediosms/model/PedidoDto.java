package org.example.pediosms.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoDto {
    private Long id;
    private LocalDateTime fechaPedido;
    private Boolean estado;
}