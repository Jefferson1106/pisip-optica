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
		
		return webClient.get().uri("api/detalleexamen/all").retrieve().
				bodyToFlux(DetalleExamenResponseDto.class).
				collectList().block();
	}

	@Override
	public void guardarDetalleExamen(DetalleExamenRequestDto nuevoDetalleExamen) {
		webClient.post().uri("api/detalleexamen").bodyValue(nuevoDetalleExamen).retrieve().toBodilessEntity().block();
		
		
	}
	
	

}
