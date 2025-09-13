package org.example.compositionpedido.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDto {

    @NotBlank(message = "El cliente no puede ser nulo ni vacío")
    private String cliente;

    @Valid
    private List<DetallePedidoRequestDto> detalles;
}