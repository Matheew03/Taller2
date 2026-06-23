package com.itsqmet.taller2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de compra es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaCompra;

    @NotNull(message = "El monto total es obligatorio")
    @Positive(message = "El monto total debe ser mayor a 0")
    private Double montoTotal;

    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "EFECTIVO|TARJETA|TRANSFERENCIA",
            message = "Método de pago debe ser EFECTIVO, TARJETA o TRANSFERENCIA")
    private String metodoPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
