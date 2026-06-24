package com.itsqmet.taller2.controller;

import com.itsqmet.taller2.model.Videojuego;
import com.itsqmet.taller2.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    @GetMapping
    public List<Videojuego> listarVideojuegos() {
        return videojuegoService.listarVideojuegos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> buscarPorId(@PathVariable Long id) {
        return videojuegoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Videojuego guardarVideojuego(@Valid @RequestBody Videojuego videojuego) {
        return videojuegoService.guardarVideojuego(videojuego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizarVideojuego(
            @PathVariable Long id,
            @Valid @RequestBody Videojuego videojuego) {

        try {
            Videojuego actualizado =
                    videojuegoService.actualizarVideojuego(id, videojuego);

            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideojuego(@PathVariable Long id) {

        if (videojuegoService.buscarPorId(id).isPresent()) {
            videojuegoService.eliminarVideojuego(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}