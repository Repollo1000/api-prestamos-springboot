package com.prestamos.api.controller;

import com.prestamos.api.model.Prestamo;
import com.prestamos.api.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    // POST /prestamos
    @PostMapping
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        Prestamo nuevo = prestamoService.crearPrestamo(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // GET /prestamos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.obtenerPrestamo(id));
    }

    // PUT /prestamos/{id}/estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<Prestamo> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("nuevoEstado");
        return ResponseEntity.ok(prestamoService.cambiarEstado(id, nuevoEstado));
    }
}