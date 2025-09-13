package org.example.stockms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockUpdateDto {
    @NotNull
    @Min(1)
    private Integer productId;

    @NotNull(message = "El almacén es obligatorio")
    @Min(value = 0,message = "El almacén no puede ser negativo")
    private Integer whareHouse;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1 , message = "La cantidad no puede ser menor a 1")
    private Integer quantity;
}
