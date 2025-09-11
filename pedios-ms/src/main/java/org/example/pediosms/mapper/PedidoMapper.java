package org.example.pediosms.mapper;


import org.example.pediosms.model.Pedido;
import org.example.pediosms.model.PedidoRequestDto;
import org.example.pediosms.model.PedidoResponseDto;
import org.example.pediosms.model.PedidoUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {


    List<PedidoResponseDto>  toListResponseDto(List<Pedido> pedidos);

    PedidoResponseDto toResponseDto(Pedido pedido);

    Pedido toEntity(PedidoRequestDto pedidoRequestDto);

    Pedido toEntity(PedidoUpdateDto pedidoUpdateDto);


    void updatePedidoFromDto(PedidoRequestDto dto, @MappingTarget Pedido pedido);

    default Pedido toEntity(PedidoRequestDto dto, Boolean estadoPorDefecto) {
        Pedido pedido = new Pedido();
        updatePedidoFromDto(dto, pedido);
        if(pedido.getEstado() == null) {
            pedido.setEstado(estadoPorDefecto != null ? estadoPorDefecto : true);
        }
        return pedido;
    }
}