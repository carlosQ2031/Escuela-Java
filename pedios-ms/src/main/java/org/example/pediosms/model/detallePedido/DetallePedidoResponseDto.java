package org.example.pediosms.model.detallePedido;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallePedidoResponseDto {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}