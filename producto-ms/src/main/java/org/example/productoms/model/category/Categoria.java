package org.example.productoms.model.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.productoms.model.product.Producto;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3)
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números")
    private String nombre;

    @NotNull
    private Boolean active = true;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference
    private List<Producto> productos = new ArrayList<>();

}
