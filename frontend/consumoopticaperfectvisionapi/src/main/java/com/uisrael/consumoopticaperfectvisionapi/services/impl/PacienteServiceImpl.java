package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
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
    public void guardarPaciente(PacienteRequestDto nuevo) {
        webClient.post().uri("/api/paciente")
                .bodyValue(nuevo)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
