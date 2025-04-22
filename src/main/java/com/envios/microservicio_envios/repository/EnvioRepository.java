package com.envios.microservicio_envios.repository;

import com.envios.microservicio_envios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {
}
