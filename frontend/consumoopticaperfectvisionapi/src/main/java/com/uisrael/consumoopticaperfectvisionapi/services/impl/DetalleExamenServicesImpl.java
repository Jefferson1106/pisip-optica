package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleExamenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleExamen;

public class DetalleExamenServicesImpl implements IDetalleExamen {
	
	private final WebClient webClient;
	
	public DetalleExamenServicesImpl(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public List<DetalleExamenResponseDto> listarDetalleExamen() {
		return webClient.get()
				.uri("/api/detalle-examen")
				.retrieve()
				.bodyToFlux(DetalleExamenResponseDto.class)
				.collectList()
				.block();
	}

	@Override
	public void guardarDetalleExamen(DetalleExamenRequestDto nuevoDetalleExamen) {
		webClient.post()
				.uri("/api/detalle-examen")
				.bodyValue(nuevoDetalleExamen)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

	@Override
	public DetalleExamenResponseDto buscarDetalleExamenPorId(Integer idDetExamen) {
		return webClient.get()
				.uri("/api/detalle-examen/{id}", idDetExamen)
				.retrieve()
				.bodyToMono(DetalleExamenResponseDto.class)
				.block();
	}

	@Override
	public void actualizarDetalleExamen(Integer idDetExamen, DetalleExamenRequestDto detalleExamen) {
		webClient.put()
				.uri("/api/detalle-examen/{id}", idDetExamen)
				.bodyValue(detalleExamen)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

	@Override
	public void eliminarDetalleExamen(Integer idDetExamen) {
		webClient.delete()
				.uri("/api/detalle-examen/{id}", idDetExamen)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

}
