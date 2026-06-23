package com.itsqmet.taller2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El total no puede ser nulo")
    @PositiveOrZero(message = "El total debe ser mayor o igual a 0")
    private Double total;

    @NotBlank(message = "El estado del carrito es obligatorio")
    @Pattern(regexp = "ACTIVO|CERRADO", message = "El estado debe ser ACTIVO o CERRADO")
    private String estado;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
