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
		
		return webClient.get().uri("/ordenespedido").retrieve().
				bodyToFlux(OrdenPedidoResponseDto.class).
				collectList().block();
	}

	@Override
	public void guardarOrdenPedido(OrdenPedidoRequestDto nuevoOrdenPedido) {
		webClient.post().uri("/ordenespedido").bodyValue(nuevoOrdenPedido).retrieve().toBodilessEntity().block();
		
		
	}

	@Override
	public void actualizarOdenPedido(OrdenPedidoRequestDto ordenPedido) {
		webClient.put()
			.uri("/ordenespedido" + ordenPedido.getIdEstadoPedido())
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
		// TODO Auto-generated method stub
		return null;
	}

}
