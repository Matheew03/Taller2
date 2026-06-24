package com.itsqmet.taller2.controller;

import com.itsqmet.taller2.model.Carrito;
import com.itsqmet.taller2.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> listarCarritos() {
        return carritoService.listarCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarPorId(@PathVariable Long id) {
        return carritoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrito guardarCarrito(@Valid @RequestBody Carrito carrito) {
        return carritoService.guardarCarrito(carrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(
            @PathVariable Long id,
            @Valid @RequestBody Carrito carrito) {

        try {
            Carrito actualizado = carritoService.actualizarCarrito(id, carrito);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {

        if (carritoService.buscarPorId(id).isPresent()) {
            carritoService.eliminarCarrito(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}