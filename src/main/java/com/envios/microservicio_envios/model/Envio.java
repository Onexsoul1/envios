package com.envios.microservicio_envios.model;

public class Envio {
    private int id;
    private String destinatario;
    private String ubicacionActual;
    private String estado;

    public Envio(int id, String destinatario, String ubicacionActual, String estado) {
        this.id = id;
        this.destinatario = destinatario;
        this.ubicacionActual = ubicacionActual;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(String ubicacionActual) { this.ubicacionActual = ubicacionActual; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
