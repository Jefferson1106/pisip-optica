package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.CatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICatalogoService;

@Service
public class CatalogoServiceImpl implements ICatalogoService {

	private final WebClient webClient;

	public CatalogoServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public List<CatalogoResponseDto> listarCatalogos() {
		return webClient.get().uri("/api/catalogos").retrieve()
				.bodyToFlux(CatalogoResponseDto.class).collectList().block();
	}

	@Override
	public CatalogoResponseDto buscarPorId(Integer idCatalogo) {
		return webClient.get().uri("/api/catalogos/" + idCatalogo).retrieve()
				.bodyToMono(CatalogoResponseDto.class).block();
	}

	@Override
	public void guardarCatalogo(CatalogoRequestDto nuevoCatalogo) {
		webClient.post().uri("/api/catalogos").bodyValue(nuevoCatalogo).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(CatalogoResponseDto.class).block();
	}

	@Override
	public void actualizarCatalogo(Integer idCatalogo, CatalogoRequestDto catalogo) {
		webClient.put().uri("/api/catalogos/" + idCatalogo).bodyValue(catalogo).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(CatalogoResponseDto.class).block();
	}
}