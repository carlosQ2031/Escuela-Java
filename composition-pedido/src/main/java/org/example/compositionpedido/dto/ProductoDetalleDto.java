package org.example.compositionpedido.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDetalleDto {
    private Long productoId;
    private String nombre;
    private BigDecimal precioUnitario;
    private Integer cantidad;
    private BigDecimal subtotal;
}