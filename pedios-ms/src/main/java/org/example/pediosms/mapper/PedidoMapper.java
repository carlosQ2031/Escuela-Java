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


    List<PedidoResponseDto>  toListResponseDto(List<Pedido> pedidos);

    PedidoResponseDto toResponseDto(Pedido pedido);

    default Pedido toEntity(PedidoRequestDto dto) {
        if(dto == null) return null;
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setEstado(true); // por defecto activo
        return pedido;
    }

    Pedido toEntity(PedidoUpdateDto pedidoUpdateDto);


    void updatePedidoFromDto(PedidoRequestDto dto, @MappingTarget Pedido pedido);



}