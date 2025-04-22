package com.envios.microservicio_envios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El destinatario es obligatorio")
    @Size(max = 100, message = "El destinatario no puede tener más de 100 caracteres")
    private String destinatario;

    @NotBlank(message = "La ubicación actual es obligatoria")
    @Size(max = 100, message = "La ubicación no puede tener más de 100 caracteres")
    @Column(name = "ubicacion_actual")
    private String ubicacionActual;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede tener más de 50 caracteres")
    private String estado;

    public Envio() {}

    public Envio(int id, String destinatario, String ubicacionActual, String estado) {
        this.id = id;
        this.destinatario = destinatario;
        this.ubicacionActual = ubicacionActual;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(String ubicacionActual) { this.ubicacionActual = ubicacionActual; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
