package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenPedidoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;

public class OrdenPedido implements IOrdenPedido {
	
	private final WebClient webClient;

	public OrdenPedido(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public List<OrdenPedidoResponseDto> listarOrdenPedido() {
		
		return webClient.get().uri("/ordenpedido").retrieve().
				bodyToFlux(OrdenPedidoResponseDto.class).
				collectList().block();
	}

	@Override
	public void guardarOrdenPedido(OrdenPedidoRequestDto nuevoOrdenPedido) {
		webClient.post().uri("/grupo").bodyValue(nuevoOrdenPedido).retrieve().toBodilessEntity().block();
		
		
	}

}
