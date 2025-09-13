package org.example.compositionpedido.service;

import org.example.compositionpedido.dto.*;
import org.example.compositionpedido.feign.PedidoClient;
import org.example.compositionpedido.feign.ProductoClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


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
            BigDecimal subtotal = prod.getPrecio().multiply(BigDecimal.valueOf(det.getCantidad()));
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
        BigDecimal total = productosDetalle.stream()
                .map(ProductoDetalleDto::getSubtotal) // BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        // Combinar en DTO de composición
        PedidoCompositionDto compositionDto = new PedidoCompositionDto();
        compositionDto.setPedidoId(pedidoCreado.getId());
        compositionDto.setCliente(pedidoCreado.getCliente());
        compositionDto.setProductos(productosDetalle);
        compositionDto.setTotal(total);

        return compositionDto;

    }


    public List<PedidoCompositionDto> obtenerTodosPedidos() {
        List<PedidoResponseDto> pedidos = pedidoClient.obtenerTodosPedidos();

        return pedidos.stream().map(pedido -> {
            PedidoCompositionDto dto = new PedidoCompositionDto();
            dto.setPedidoId(pedido.getId());
            dto.setCliente(pedido.getCliente());

            if (pedido.getDetalles() != null && !pedido.getDetalles().isEmpty()) {
                List<ProductoDetalleDto> productos = pedido.getDetalles().stream().map(det -> {
                    ProductoDetalleDto pd = new ProductoDetalleDto();
                    pd.setProductoId(det.getProductoId());
                    pd.setCantidad(det.getCantidad());
                    pd.setPrecioUnitario(det.getPrecioUnitario() != null ? det.getPrecioUnitario() : BigDecimal.ZERO);
                    pd.setSubtotal(det.getSubtotal() != null ? det.getSubtotal() : BigDecimal.ZERO);
                    //pd.setNombre(det.getNombre() != null ? det.getNombre() : "Sin nombre");
                    return pd;
                }).collect(Collectors.toList());

                dto.setProductos(productos);

                BigDecimal total = productos.stream()
                        .map(ProductoDetalleDto::getSubtotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                dto.setTotal(total);
            } else {
                dto.setProductos(List.of());
                dto.setTotal(BigDecimal.ZERO);
            }

            return dto;
        }).collect(Collectors.toList());
    }
}
