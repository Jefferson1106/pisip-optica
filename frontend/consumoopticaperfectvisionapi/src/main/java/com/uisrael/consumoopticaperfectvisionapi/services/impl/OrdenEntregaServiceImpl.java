package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenEntregaService;

@Service
public class OrdenEntregaServiceImpl implements IOrdenEntregaService {

    private final WebClient webClient;

    public OrdenEntregaServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<OrdenEntregaResponseDto> listarOrdenes() {
        return webClient.get()
            .uri("/api/orden-entregas")
            .retrieve()
            .bodyToFlux(OrdenEntregaResponseDto.class)
            .collectList()
            .block();
    }

    @Override
    public void guardarOrden(OrdenEntregaRequestDto orden) {
        webClient.post()
            .uri("/api/orden-entregas")
            .bodyValue(orden)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

    @Override
    public OrdenEntregaResponseDto buscarPorId(Integer idEntrega) {
        return webClient.get()
            .uri("/api/orden-entregas/{id}", idEntrega)
            .retrieve()
            .bodyToMono(OrdenEntregaResponseDto.class)
            .block();
    }

    @Override
    public void actualizarOrden(Integer idEntrega, OrdenEntregaRequestDto orden) {
        webClient.put()
                 .uri("/api/orden-entregas/{id}", idEntrega) // <-- aquí va idEntrega
                 .bodyValue(orden)                           // body con RequestDto
                 .retrieve()
                 .bodyToMono(OrdenEntregaResponseDto.class)  // el backend devuelve ResponseDto
                 .block();
    }

    @Override
    public void eliminarOrden(Integer idEntrega) {
        webClient.delete()
            .uri("/api/orden-entregas/{id}", idEntrega)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }
}
