package org.example.compositionpedido.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoCompositionDto {
    private Long pedidoId;
    private String cliente;
    private List<ProductoDetalleDto> productos;
    private BigDecimal total;
}
