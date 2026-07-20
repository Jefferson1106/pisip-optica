package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleOrdenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleOrden;

@Service
public class DetalleOrdenServicesImpl implements IDetalleOrden {
	
	private final WebClient webClient;
	
	
	public DetalleOrdenServicesImpl(WebClient webClient) {
		super();
		this.webClient = webClient;
	}


	@Override
	public List<DetalleOrdenResponseDto> listarDetalleOrden() {
		
		return webClient.get().uri("api/detalleorden/all").retrieve().
				bodyToFlux(DetalleOrdenResponseDto.class).
				collectList().block();
	}


	@Override
	public void guardarDetalleOrden(DetalleOrdenRequestDto nuevoDetalleOrden) {
		webClient.post().uri("api/detalleorden").bodyValue(nuevoDetalleOrden).retrieve().toBodilessEntity().block();
		
		
	}



}
