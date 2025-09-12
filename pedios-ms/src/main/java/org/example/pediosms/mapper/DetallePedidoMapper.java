package org.example.pediosms.mapper;


import org.example.pediosms.model.detallePedido.DetallePedido;
import org.example.pediosms.model.detallePedido.DetallePedidoRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    //List<DetallePedido> <- List<DetallePedidorRequestDto>
    List<DetallePedido> toEntityList(List<DetallePedidoRequestDto> detallePedidoRequestDto);

    //DetallePedido <- DetallePedidoRequestDto
    DetallePedido toEntity(DetallePedidoRequestDto dto);

}