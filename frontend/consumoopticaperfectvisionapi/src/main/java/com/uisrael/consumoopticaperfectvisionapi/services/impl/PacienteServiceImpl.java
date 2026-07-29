package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;

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
                response.bodyToMono(String.class)
                        .defaultIfEmpty("Solicitud invalida al registrar paciente")
                        .map(body -> new RuntimeException(extraerMensajeError(body, "Solicitud invalida al registrar paciente")))
            )
            // Manejo de errores 5xx (fallos del servidor)
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                response.bodyToMono(String.class)
                        .defaultIfEmpty("Error del servidor al registrar paciente")
                        .map(msg -> new RuntimeException("Error del servidor: " + extraerMensajeError(msg, "Error interno")))
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
                response.bodyToMono(String.class)
                        .defaultIfEmpty("Solicitud invalida al actualizar paciente")
                        .map(body -> new RuntimeException(extraerMensajeError(body, "Solicitud invalida al actualizar paciente")))
            )
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                response.bodyToMono(String.class)
                        .defaultIfEmpty("Error del servidor al actualizar paciente")
                        .map(msg -> new RuntimeException("Error del servidor: " + extraerMensajeError(msg, "Error interno")))
            )
            .bodyToMono(Void.class)
            .block();
    }

    private String extraerMensajeError(String body, String mensajePorDefecto) {
        if (body == null || body.isBlank()) {
            return mensajePorDefecto;
        }

        String texto = body.trim();
        if (!texto.startsWith("{")) {
            return texto;
        }

        for (String key : new String[] { "message", "error", "mensaje", "detail", "descripcion" }) {
            String marker = "\"" + key + "\"";
            int keyIndex = texto.indexOf(marker);
            if (keyIndex < 0) {
                continue;
            }
            int colonIndex = texto.indexOf(':', keyIndex + marker.length());
            if (colonIndex < 0) {
                continue;
            }
            int firstQuote = texto.indexOf('"', colonIndex + 1);
            if (firstQuote < 0) {
                continue;
            }
            int secondQuote = texto.indexOf('"', firstQuote + 1);
            if (secondQuote < 0) {
                continue;
            }
            String value = texto.substring(firstQuote + 1, secondQuote).trim();
            if (!value.isBlank()) {
                return value;
            }
        }

        return texto;
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
