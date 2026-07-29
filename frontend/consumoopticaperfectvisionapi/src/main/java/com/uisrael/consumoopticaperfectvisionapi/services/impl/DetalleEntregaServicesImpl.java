package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleEntrega;

public class DetalleEntregaServicesImpl implements IDetalleEntrega {

    private final WebClient webClient;

    public DetalleEntregaServicesImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<DetalleEntregaResponseDto> listarDetalleEntrega() {
        return webClient.get()
                .uri("/api/detalle-entregas")
                .retrieve()
                .bodyToFlux(DetalleEntregaResponseDto.class)
                .collectList()
                .block();
    }

    @Override
    public DetalleEntregaResponseDto buscarDetalleEntregaPorId(Integer idDetEntrega) {
        return webClient.get()
                .uri("/api/detalle-entregas/{id}", idDetEntrega)
                .retrieve()
                .bodyToMono(DetalleEntregaResponseDto.class)
                .block();
    }

    @Override
    public void guardarDetalleEntrega(DetalleEntregaRequestDto nuevoDetalleEntrega) {
        webClient.post()
                .uri("/api/detalle-entregas")
                .bodyValue(nuevoDetalleEntrega)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public void actualizarDetalleEntrega(Integer idDetEntrega, DetalleEntregaRequestDto detalleEntrega) {
        webClient.put()
                .uri("/api/detalle-entregas/{id}", idDetEntrega)
                .bodyValue(detalleEntrega)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public void eliminarDetalleEntrega(Integer idDetEntrega) {
        webClient.delete()
                .uri("/api/detalle-entregas/{id}", idDetEntrega)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
