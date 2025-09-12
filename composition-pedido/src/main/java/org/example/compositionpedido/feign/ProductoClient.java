package org.example.compositionpedido.feign;

import org.example.compositionpedido.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="producto-ms",url = "http://localhost:8081/api/productos")
public interface ProductoClient {
    @GetMapping("/{id}")
    ProductoDto obtenerProducto(@PathVariable Long id);
}
