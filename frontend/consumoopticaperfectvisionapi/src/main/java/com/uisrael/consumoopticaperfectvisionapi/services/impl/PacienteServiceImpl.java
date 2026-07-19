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

    
    //actualizar validar
    @Override
    public void actualizarPaciente(PacienteRequestDto paciente) {
        webClient.put()
            .uri("/api/paciente/" + paciente.getIdPaciente()) // o cedula, según tu API
            .bodyValue(paciente) // envía TODOS los campos
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response ->
                response.bodyToMono(Map.class)
                        .map(body -> new RuntimeException((String) body.get("error")))
            )
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                response.bodyToMono(String.class)
                        .map(msg -> new RuntimeException("Error del servidor: " + msg))
            )
            .bodyToMono(Void.class)
            .block();
    }

	@Override
	public PacienteResponseDto buscarPorId(Long idPaciente) {
	    return webClient.get()
	        .uri("/api/paciente/" + idPaciente)
	        .retrieve()
	        .bodyToMono(PacienteResponseDto.class)
	        .block();
	}

	@Override
	public void eliminarPaciente(Long idPaciente) {
	    // Primero obtienes el paciente
	    PacienteResponseDto paciente = webClient.get()
	        .uri("/api/paciente/" + idPaciente)
	        .retrieve()
	        .bodyToMono(PacienteResponseDto.class)
	        .block();

	    // Marcas como inactivo
	    paciente.setActivo(false);

	    // Envías la actualización al backend
	    webClient.put()
	        .uri("/api/paciente/" + idPaciente)
	        .bodyValue(paciente)
	        .retrieve()
	        .bodyToMono(Void.class)
	        .block();
	}


}
