package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenPedidoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;

public class OrdenPedidoServicesImpl implements IOrdenPedido {


	private final WebClient webClient;

	public OrdenPedidoServicesImpl(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public List<OrdenPedidoResponseDto> listarOrdenPedido() {
		
		return webClient.get().uri("/api/detalle-ordenPedido").retrieve().
				bodyToFlux(OrdenPedidoResponseDto.class).
				collectList().block();
	}

	@Override
	public void guardarOrdenPedido(OrdenPedidoRequestDto nuevoOrdenPedido) {
		webClient.post().uri("/api/detalle-ordenPedido").bodyValue(nuevoOrdenPedido).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error", "Validación inválida"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(OrdenPedidoResponseDto.class).block();
		
		
	}

	@Override
	public void actualizarOdenPedido(Long id, OrdenPedidoRequestDto ordenPedido) {
		webClient.put()
			.uri("/api/detalle-ordenPedido/" + id)
			.bodyValue(ordenPedido)
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
	public OrdenPedidoResponseDto buscarPorId(Long id) {
		return webClient.get()
				.uri("/api/detalle-ordenPedido/" + id)
				.retrieve()
				.bodyToMono(OrdenPedidoResponseDto.class)
				.block();
	}

	@Override
	public void eliminarOrdenPedido(Long id) {
		webClient.delete()
				.uri("/api/detalle-ordenPedido/" + id)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("error",
								"No se puede eliminar la orden porque tiene dependencias"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.toBodilessEntity()
				.block();
	}

}
