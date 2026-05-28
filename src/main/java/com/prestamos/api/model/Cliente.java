package com.prestamos.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombre;
    private String rut;
    private String email;

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }
    public void setRut(String rut){
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
