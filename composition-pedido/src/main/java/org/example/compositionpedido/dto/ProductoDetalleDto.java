package org.example.compositionpedido.dto;

import lombok.Data;

@Data
public class ProductoDetalleDto {
    private Long productoId;
    private String nombre;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;
}