package com.itsqmet.taller2.controller;

import com.itsqmet.taller2.model.Compra;
import com.itsqmet.taller2.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> listarCompras() {
        return compraService.listarCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Compra guardarCompra(@Valid @RequestBody Compra compra) {
        return compraService.guardarCompra(compra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> actualizarCompra(
            @PathVariable Long id,
            @Valid @RequestBody Compra compra) {

        try {
            Compra actualizada =
                    compraService.actualizarCompra(id, compra);

            return ResponseEntity.ok(actualizada);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {

        if (compraService.buscarPorId(id).isPresent()) {
            compraService.eliminarCompra(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}