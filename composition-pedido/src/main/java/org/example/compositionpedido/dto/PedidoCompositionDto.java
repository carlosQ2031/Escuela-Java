package org.example.compositionpedido.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoCompositionDto {
    private Long pedidoId;
    private String cliente;
    private List<ProductoDetalleDto> productos;
    private Double total;
}
