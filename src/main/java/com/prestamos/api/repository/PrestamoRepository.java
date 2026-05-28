package com.prestamos.api.repository;


import com.prestamos.api.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // para la regla de negocio: max 3 préstamos pendientes
    long countByClienteIdAndEstado(Long clienteId, String estado);

    // para traer todos los préstamos de un cliente
    List<Prestamo> findByClienteId(Long clienteId);
}
