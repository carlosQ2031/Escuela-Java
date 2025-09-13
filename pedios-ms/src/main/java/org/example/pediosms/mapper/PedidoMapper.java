package org.example.pediosms.mapper;


import org.example.pediosms.model.detallePedido.DetallePedido;
import org.example.pediosms.model.detallePedido.DetallePedidoResponseDto;
import org.example.pediosms.model.pedido.Pedido;
import org.example.pediosms.model.pedido.PedidoRequestDto;
import org.example.pediosms.model.pedido.PedidoResponseDto;
import org.example.pediosms.model.pedido.PedidoUpdateDto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    // PedidoRequestDto -> Pedido
    default Pedido toEntity(PedidoRequestDto pedidoRequestDto) {
        if (pedidoRequestDto == null) return null;

        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoRequestDto.getCliente());

        if (pedidoRequestDto.getDetalles() != null) {
            pedidoRequestDto.getDetalles().forEach(detDto -> {
                DetallePedido detalle = new DetallePedido();
                detalle.setCantidad(detDto.getCantidad());
                detalle.setPrecioUnitario(detDto.getPrecioUnitario());
                detalle.setSubtotal(detDto.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(detDto.getCantidad())));
                detalle.setProductoId(detDto.getProductoId());
                detalle.setPedido(pedido); // vínculo con el pedido
                pedido.getDetallePedidos().add(detalle);
            });
        }

        return pedido;
    }

    // PedidoUpdateDto -> Pedido
    Pedido toEntity(PedidoUpdateDto pedidoUpdateDto);

    // Lista de Pedidos -> Lista de PedidoResponseDto
    default List<PedidoResponseDto> toListResponseDto(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // Pedido -> PedidoResponseDto
    default PedidoResponseDto toResponseDto(Pedido pedido) {
        if (pedido == null) return null;

        PedidoResponseDto dto = new PedidoResponseDto();
        dto.setId(pedido.getId());
        dto.setCliente(pedido.getCliente());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());

        if (pedido.getDetallePedidos() != null) {
            List<DetallePedidoResponseDto> detalles = pedido.getDetallePedidos()
                    .stream()
                    .map(det -> {
                        DetallePedidoResponseDto d = new DetallePedidoResponseDto();
                        d.setId(det.getId());
                        d.setProductoId(det.getProductoId());
                        d.setCantidad(det.getCantidad());
                        d.setPrecioUnitario(det.getPrecioUnitario());
                        d.setSubtotal(det.getSubtotal());
                        return d;
                    })
                    .collect(Collectors.toList());
            dto.setDetalles(detalles);
        }

        return dto;
    }
}