package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleOrdenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleOrden;

public class DetalleOrdenServicesImpl implements IDetalleOrden {
	
	private final WebClient webClient;
	
	
	public DetalleOrdenServicesImpl(WebClient webClient) {
		super();
		this.webClient = webClient;
	}


	@Override
	public List<DetalleOrdenResponseDto> listarDetalleOrden() {
		return webClient.get()
				.uri("/api/detalle-orden")
				.retrieve()
				.bodyToFlux(DetalleOrdenResponseDto.class)
				.collectList()
				.block();
	}


	@Override
	public void guardarDetalleOrden(DetalleOrdenRequestDto nuevoDetalleOrden) {
		webClient.post()
				.uri("/api/detalle-orden")
				.bodyValue(nuevoDetalleOrden)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

	@Override
	public DetalleOrdenResponseDto buscarDetalleOrdenPorId(Integer idDetOrden) {
		return webClient.get()
				.uri("/api/detalle-orden/{id}", idDetOrden)
				.retrieve()
				.bodyToMono(DetalleOrdenResponseDto.class)
				.block();
	}

	@Override
	public void actualizarDetalleOrden(Integer idDetOrden, DetalleOrdenRequestDto detalleOrden) {
		webClient.put()
				.uri("/api/detalle-orden/{id}", idDetOrden)
				.bodyValue(detalleOrden)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

	@Override
	public void eliminarDetalleOrden(Integer idDetOrden) {
		webClient.delete()
				.uri("/api/detalle-orden/{id}", idDetOrden)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

}
