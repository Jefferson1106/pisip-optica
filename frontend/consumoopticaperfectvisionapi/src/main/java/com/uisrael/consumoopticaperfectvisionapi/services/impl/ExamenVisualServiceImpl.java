package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ExamenVisualRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;

@Service
public class ExamenVisualServiceImpl implements IExamenVisualService {

	private final WebClient webClient;

	public ExamenVisualServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public List<ExamenVisualResponseDto> listarExamenesVisuales() {
		return webClient.get().uri("/api/examenes-visuales").retrieve()
				.bodyToFlux(ExamenVisualResponseDto.class).collectList().block();
	}

	@Override
	public ExamenVisualResponseDto buscarPorId(Integer idExamenVisual) {
		return webClient.get().uri("/api/examenes-visuales/" + idExamenVisual).retrieve()
				.bodyToMono(ExamenVisualResponseDto.class).block();
	}

	@Override
	public void guardarExamenVisual(ExamenVisualRequestDto nuevoExamenVisual) {
		webClient.post().uri("/api/examenes-visuales").bodyValue(nuevoExamenVisual).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(ExamenVisualResponseDto.class).block();
	}

	@Override
	public void actualizarExamenVisual(Integer idExamenVisual, ExamenVisualRequestDto examenVisual) {
		webClient.put().uri("/api/examenes-visuales/" + idExamenVisual).bodyValue(examenVisual).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(ExamenVisualResponseDto.class).block();
	}
}