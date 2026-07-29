package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ProveedorRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ProveedorResponseDto;

@Service
public class ProveedorService {
	private final WebClient webClient;
	public ProveedorService(WebClient webClient) { this.webClient = webClient; }
	public List<ProveedorResponseDto> listar() {
		return webClient.get().uri("/api/proveedores").retrieve().bodyToFlux(ProveedorResponseDto.class).collectList().block();
	}
	public ProveedorResponseDto buscar(Integer id) {
		return webClient.get().uri("/api/proveedores/{id}", id).retrieve().bodyToMono(ProveedorResponseDto.class).block();
	}
	public void guardar(ProveedorRequestDto dto) { enviar(dto, null); }
	public void actualizar(Integer id, ProveedorRequestDto dto) { enviar(dto, id); }
	private void enviar(ProveedorRequestDto dto, Integer id) {
		var spec = id == null ? webClient.post().uri("/api/proveedores") : webClient.put().uri("/api/proveedores/{id}", id);
		spec.bodyValue(dto).retrieve()
			.onStatus(HttpStatusCode::isError, r -> r.bodyToMono(Map.class)
				.map(b -> new RuntimeException(String.valueOf(b.getOrDefault("error", "No se pudo guardar el proveedor")))))
			.toBodilessEntity().block();
	}
}
