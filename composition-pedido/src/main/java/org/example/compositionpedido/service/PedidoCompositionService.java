package org.example.compositionpedido.service;

import org.example.compositionpedido.dto.*;
import org.example.compositionpedido.feign.PedidoClient;
import org.example.compositionpedido.feign.ProductoClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoCompositionService {

    private final ProductoClient productoClient;
    private final PedidoClient pedidoClient;

    public PedidoCompositionService(PedidoClient pedidoClient, ProductoClient productoClient) {
        this.pedidoClient = pedidoClient;
        this.productoClient = productoClient;
    }

    public PedidoCompositionDto crearPedido(PedidoRequestDto pedidoRequestDto) {
        List<ProductoDetalleDto> productosDetalle = pedidoRequestDto.getDetalles().stream().map(det -> {
            ProductoDto prod = productoClient.obtenerProducto(det.getProductoId());
            Double subtotal = prod.getPrecio() * det.getCantidad();
            ProductoDetalleDto detalle = new ProductoDetalleDto();
            detalle.setProductoId(prod.getId());
            detalle.setNombre(prod.getNombre());
            detalle.setPrecioUnitario(prod.getPrecio());
            detalle.setCantidad(det.getCantidad());
            detalle.setSubtotal(subtotal);
            return detalle;
        }).toList();
        PedidoResponseDto pedidoCreado = pedidoClient.crearPedido(pedidoRequestDto);

        // Calcular total
        Double total = productosDetalle.stream()
                .mapToDouble(ProductoDetalleDto::getSubtotal)
                .sum();

        // Combinar en DTO de composición
        PedidoCompositionDto compositionDto = new PedidoCompositionDto();
        compositionDto.setPedidoId(pedidoCreado.getId());
        compositionDto.setCliente(pedidoCreado.getCliente());
        compositionDto.setProductos(productosDetalle);
        compositionDto.setTotal(total);

        return compositionDto;

    }


}
