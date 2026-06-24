package com.itsqmet.taller2.service;

import com.itsqmet.taller2.model.Compra;
import com.itsqmet.taller2.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public Compra guardarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public Compra actualizarCompra(Long id, Compra compraActualizada) {

        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        compra.setFechaCompra(compraActualizada.getFechaCompra());
        compra.setMontoTotal(compraActualizada.getMontoTotal());
        compra.setMetodoPago(compraActualizada.getMetodoPago());
        compra.setCliente(compraActualizada.getCliente());
        compra.setVideojuegos(compraActualizada.getVideojuegos());

        return compraRepository.save(compra);
    }

    public void eliminarCompra(Long id) {
        compraRepository.deleteById(id);
    }
}