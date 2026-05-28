package com.prestamos.api.service;

import com.prestamos.api.model.Cliente;
import com.prestamos.api.model.Prestamo;
import com.prestamos.api.repository.ClienteRepository;
import com.prestamos.api.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    public Prestamo crearPrestamo(Prestamo prestamo){
        // Regla 1: cliente debe existir
        Cliente cliente = clienteRepository.findById(prestamo.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Regla 2: max 3 préstamos PENDIENTES

        long pendientes = prestamoRepository.countByClienteIdAndEstado(cliente.getId(), "PENDIENTE");

        if (pendientes >= 3){
            throw new RuntimeException("Muchos prestamos");
        }
        // Regla 3: monto debe ser mayor a 0
        if (prestamo.getMonto() <= 0){
            throw new RuntimeException("el monto es menor a 0");
        }
        // Regla 4: tasa entre 0 y 50

        if (prestamo.getTasaInteres() < 0 || prestamo.getTasaInteres() > 50){
            throw new RuntimeException("fuera de rango");
        }

        prestamo.setCliente(cliente);
        prestamo.setEstado("PENDIENTE");

        return prestamoRepository.save(prestamo);
    }

    public Prestamo obtenerPrestamo(Long id){

        return prestamoRepository.findById(id).orElseThrow(() -> new RuntimeException("no encontrado"));

    }

    public Prestamo cambiarEstado(Long id, String nuevoEstado){

        Prestamo prestamo = obtenerPrestamo(id);

        if (!prestamo.getEstado().equals("PENDIENTE")){
            throw new RuntimeException("Solo se pueden modificar préstamos PENDIENTES");
        }
        prestamo.setEstado(nuevoEstado);
        return prestamoRepository.save(prestamo);

    }
    public List<Prestamo> obtenerPorCliente(Long clienteId) {
        return prestamoRepository.findByClienteId(clienteId);
    }


}
