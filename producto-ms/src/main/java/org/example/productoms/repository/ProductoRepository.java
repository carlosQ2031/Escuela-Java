package org.example.productoms.repository;

import org.example.productoms.model.product.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
