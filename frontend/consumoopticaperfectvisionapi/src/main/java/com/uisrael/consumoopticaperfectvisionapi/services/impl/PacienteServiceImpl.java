package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.PacienteRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private final WebClient webClient;

    public PacienteServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<PacienteResponseDto> listarPacientes() {
        return webClient.get().uri("/api/paciente/all").retrieve()
                .bodyToFlux(PacienteResponseDto.class).collectList().block();
    }
    
    @Override
    public void guardarPaciente(PacienteRequestDto paciente) {
        webClient.post()
            .uri("/api/paciente")
            .bodyValue(paciente)
            .retrieve()
            // Manejo de errores 4xx (validaciones)
            .onStatus(HttpStatusCode::is4xxClientError, response ->
                response.bodyToMono(Map.class)
                        .map(body -> new RuntimeException((String) body.get("error")))
            )
            // Manejo de errores 5xx (fallos del servidor)
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                response.bodyToMono(String.class)
                        .map(msg -> new RuntimeException("Error del servidor: " + msg))
            )
            .bodyToMono(Void.class)
            .block();
    }

    
}
