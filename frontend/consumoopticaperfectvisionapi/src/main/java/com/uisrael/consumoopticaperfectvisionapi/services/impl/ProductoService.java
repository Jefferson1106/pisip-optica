package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ProductoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ProductoResponseDto;

@Service
public class ProductoService {
	private final WebClient webClient;
	public ProductoService(WebClient webClient) { this.webClient = webClient; }
	public List<ProductoResponseDto> listar() {
		return webClient.get().uri("/api/productos").retrieve().bodyToFlux(ProductoResponseDto.class).collectList().block();
	}
	public ProductoResponseDto buscar(Integer id) {
		return webClient.get().uri("/api/productos/{id}", id).retrieve().bodyToMono(ProductoResponseDto.class).block();
	}
	public void guardar(ProductoRequestDto dto) { enviar(dto, null); }
	public void actualizar(Integer id, ProductoRequestDto dto) { enviar(dto, id); }
	private void enviar(ProductoRequestDto dto, Integer id) {
		var spec = id == null ? webClient.post().uri("/api/productos") : webClient.put().uri("/api/productos/{id}", id);
		spec.bodyValue(dto).retrieve()
			.onStatus(HttpStatusCode::isError, r -> r.bodyToMono(Map.class)
				.map(b -> new RuntimeException(String.valueOf(b.getOrDefault("error", "No se pudo guardar el producto")))))
			.toBodilessEntity().block();
	}
}
