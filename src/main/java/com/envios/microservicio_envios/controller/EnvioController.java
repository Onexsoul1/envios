package com.envios.microservicio_envios.controller;

import com.envios.microservicio_envios.model.Envio;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private List<Envio> envios = new ArrayList<>(List.of(
        new Envio(1, "Luis Romero", "Chile", "En tránsito"),
        new Envio(2, "María Torres", "Argentina", "En aduana"),
        new Envio(3, "Pedro Méndez", "Brasil", "Entregado")
    ));

    @GetMapping
    public List<Envio> obtenerTodos() {
        return envios;
    }

    @GetMapping("/{id}")
    public Envio obtenerPorId(@PathVariable int id) {
        return envios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @GetMapping("/{id}/estado")
    public String estadoActual(@PathVariable int id) {
        Envio envio = envios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        return envio != null ? envio.getEstado() : "Envío no encontrado";
    }

    @GetMapping("/{id}/ubicacion")
    public String ubicacionActual(@PathVariable int id) {
        Envio envio = envios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        return envio != null ? envio.getUbicacionActual() : "Envío no encontrado";
    }
}
