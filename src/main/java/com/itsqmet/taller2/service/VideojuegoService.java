package com.itsqmet.taller2.service;

import com.itsqmet.taller2.model.Videojuego;
import com.itsqmet.taller2.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<Videojuego> listarVideojuegos() {
        return videojuegoRepository.findAll();
    }

    public Optional<Videojuego> buscarPorId(Long id) {
        return videojuegoRepository.findById(id);
    }

    public Videojuego guardarVideojuego(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    public Videojuego actualizarVideojuego(Long id, Videojuego videojuegoActualizado) {

        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));

        videojuego.setTitulo(videojuegoActualizado.getTitulo());
        videojuego.setCategoria(videojuegoActualizado.getCategoria());
        videojuego.setPrecio(videojuegoActualizado.getPrecio());
        videojuego.setStock(videojuegoActualizado.getStock());

        return videojuegoRepository.save(videojuego);
    }

    public void eliminarVideojuego(Long id) {
        videojuegoRepository.deleteById(id);
    }
}