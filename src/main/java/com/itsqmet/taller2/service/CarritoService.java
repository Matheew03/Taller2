package com.itsqmet.taller2.service;

import com.itsqmet.taller2.model.Carrito;
import com.itsqmet.taller2.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> buscarPorId(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito guardarCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCarrito(Long id, Carrito carritoActualizado) {

        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        carrito.setTotal(carritoActualizado.getTotal());
        carrito.setEstado(carritoActualizado.getEstado());
        carrito.setCliente(carritoActualizado.getCliente());

        return carritoRepository.save(carrito);
    }

    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

}