package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleCatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;

@Service
public class DetalleCatalogoServiceImpl implements IDetalleCatalogoService {

	private final WebClient webClient;

	public DetalleCatalogoServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public List<DetalleCatalogoResponseDto> listarDetalleCatalogos() {
		return webClient.get().uri("/api/detalle-catalogos").retrieve()
				.bodyToFlux(DetalleCatalogoResponseDto.class).collectList().block();
	}

	@Override
	public DetalleCatalogoResponseDto buscarPorId(Integer idDetalleCatalogo) {
		return webClient.get().uri("/api/detalle-catalogos/" + idDetalleCatalogo).retrieve()
				.bodyToMono(DetalleCatalogoResponseDto.class).block();
	}

	@Override
	public void guardarDetalleCatalogo(DetalleCatalogoRequestDto nuevoDetalleCatalogo) {
		webClient.post().uri("/api/detalle-catalogos").bodyValue(nuevoDetalleCatalogo).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(DetalleCatalogoResponseDto.class).block();
	}

	@Override
	public void actualizarDetalleCatalogo(Integer idDetalleCatalogo, DetalleCatalogoRequestDto detalleCatalogo) {
		webClient.put().uri("/api/detalle-catalogos/" + idDetalleCatalogo).bodyValue(detalleCatalogo).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(DetalleCatalogoResponseDto.class).block();
	}
}