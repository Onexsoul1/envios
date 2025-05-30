package com.envios.microservicio_envios;
import com.envios.microservicio_envios.controller.EnvioController;
import com.envios.microservicio_envios.model.Envio;
import com.envios.microservicio_envios.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioRepository envioRepository;

    @Test
    void deberiaRetornarEstadoEnvio() throws Exception {
        Envio envio = new Envio();
        envio.setId(1);
        envio.setDestinatario("Luis");
        envio.setUbicacionActual("Valparaíso");
        envio.setEstado("En tránsito");
        Mockito.when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
    
        mockMvc.perform(get("/envios/1/estado"))
                .andExpect(status().isOk())
                .andExpect(content().string("En tránsito")); 
    }
    
    @Test
    void deberiaRetornarUbicacionEnvio() throws Exception {
        Envio envio = new Envio();
        envio.setId(1);
        envio.setDestinatario("Luis");
        envio.setUbicacionActual("Valparaíso");
        envio.setEstado("En tránsito");
        Mockito.when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
    
        mockMvc.perform(get("/envios/1/ubicacion"))
                .andExpect(status().isOk())
                .andExpect(content().string("Valparaíso")); 
    }
    @Test
    void deberiaRetornarNotFoundSiEnvioNoExiste() throws Exception {
        Mockito.when(envioRepository.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/envios/99/estado"))
                .andExpect(status().isNotFound());
    }
    @Test
    void deberiaRetornarEnvioCompletoPorId() throws Exception {
        Envio envio = new Envio();
        envio.setId(2);
        envio.setDestinatario("Ana");
        envio.setUbicacionActual("Santiago");
        envio.setEstado("Entregado");

        Mockito.when(envioRepository.findById(2)).thenReturn(Optional.of(envio));

        mockMvc.perform(get("/envios/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destinatario").value("Ana"))
                .andExpect(jsonPath("$.ubicacionActual").value("Santiago"))
                .andExpect(jsonPath("$.estado").value("Entregado"));
    }

}
