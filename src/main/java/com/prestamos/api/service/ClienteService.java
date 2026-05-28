package com.prestamos.api.service;


import com.prestamos.api.model.Cliente;
import com.prestamos.api.model.Prestamo;
import com.prestamos.api.repository.ClienteRepository;
import com.prestamos.api.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    public Cliente guardar(Cliente cliente){

        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos(){

        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id){

        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public List<Prestamo> obtenerPrestamos(Long clienteId) {
        return prestamoRepository.findByClienteId(clienteId);
    }
}
