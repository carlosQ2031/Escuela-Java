package org.example.productoms.repository;

import org.example.productoms.model.category.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByActiveTrue();
    List<Categoria> findByActiveFalse();

}
