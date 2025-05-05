package com.envios.microservicio_envios.controller;

import com.envios.microservicio_envios.model.Envio;
import com.envios.microservicio_envios.repository.EnvioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioRepository envioRepository;

    @GetMapping
    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return envioRepository.findById(id)
            .map(envio -> {
                EntityModel<Envio> model = EntityModel.of(envio);
                model.add(linkTo(methodOn(EnvioController.class).obtenerPorId(id)).withSelfRel());
                model.add(linkTo(methodOn(EnvioController.class).obtenerTodos()).withRel("todos-envios"));
                return ResponseEntity.ok(model);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/estado")
    public ResponseEntity<String> estadoActual(@PathVariable int id) {
        return envioRepository.findById(id)
                .map(envio -> ResponseEntity.ok(envio.getEstado()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<String> ubicacionActual(@PathVariable int id) {
        return envioRepository.findById(id)
                .map(envio -> ResponseEntity.ok(envio.getUbicacionActual()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrarEnvio(@Valid @RequestBody Envio envio, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.ok(envioRepository.save(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEnvio(@PathVariable int id, @Valid @RequestBody Envio envioActualizado, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
            return ResponseEntity.badRequest().body(errores);
        }

        return envioRepository.findById(id)
                .map(envio -> {
                    envio.setDestinatario(envioActualizado.getDestinatario());
                    envio.setUbicacionActual(envioActualizado.getUbicacionActual());
                    envio.setEstado(envioActualizado.getEstado());
                    return ResponseEntity.ok(envioRepository.save(envio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable int id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
            return ResponseEntity.ok("Envío eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("Envío no encontrado");
        }
    }
}
