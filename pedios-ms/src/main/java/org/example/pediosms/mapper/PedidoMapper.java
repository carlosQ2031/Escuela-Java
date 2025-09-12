package org.example.pediosms.mapper;


import org.example.pediosms.model.pedido.Pedido;
import org.example.pediosms.model.pedido.PedidoRequestDto;
import org.example.pediosms.model.pedido.PedidoResponseDto;
import org.example.pediosms.model.pedido.PedidoUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    //List<PedidoResponseDto> <- List<Pedido>
    List<PedidoResponseDto>  toListResponseDto(List<Pedido> pedidos);

    //PedidoResponseDto <- Pedido
    PedidoResponseDto toResponseDto(Pedido pedido);

    //Pedido <- PedidoRequestDto
    default Pedido toEntity(PedidoRequestDto pedidoRequestDto) {
        if(pedidoRequestDto == null) return null;
        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoRequestDto.getCliente());
        return pedido;
    }

    //Pedido <- PedidoUpdateDto
    Pedido toEntity(PedidoUpdateDto pedidoUpdateDto);


}