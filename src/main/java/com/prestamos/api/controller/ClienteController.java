package com.prestamos.api.controller;

import com.prestamos.api.model.Cliente;
import com.prestamos.api.model.Prestamo;
import com.prestamos.api.repository.PrestamoRepository;
import com.prestamos.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping
    //obtener los clientes
    public ResponseEntity<List<Cliente>> obtenerTodos(){
        return ResponseEntity.ok(clienteService.obtenerTodos());  // JSON real
    }
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        Cliente nuevo = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

    }
    // GET /clientes/{id}/prestamos
    @GetMapping("/{id}/prestamos")
    public List<Prestamo> obtenerPrestamos(Long clienteId) {
        return prestamoRepository.findByClienteId(clienteId);
    }

}