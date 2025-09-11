package org.example.pediosms.mapper;


import org.example.pediosms.model.detallePedido.DetallePedido;
import org.example.pediosms.model.detallePedido.DetallePedidoRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    List<DetallePedido> toEntityList(List<DetallePedidoRequestDto> dtos);

    DetallePedido toEntity(DetallePedidoRequestDto dto);

}