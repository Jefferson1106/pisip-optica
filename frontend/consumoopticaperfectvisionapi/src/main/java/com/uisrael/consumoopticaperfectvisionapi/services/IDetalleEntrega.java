package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleEntregaResponseDto;

public interface IDetalleEntrega {

    List<DetalleEntregaResponseDto> listarDetalleEntrega();

    DetalleEntregaResponseDto buscarDetalleEntregaPorId(Integer idDetEntrega);

    void guardarDetalleEntrega(DetalleEntregaRequestDto nuevoDetalleEntrega);

    void actualizarDetalleEntrega(Integer idDetEntrega, DetalleEntregaRequestDto detalleEntrega);

    void eliminarDetalleEntrega(Integer idDetEntrega);
}
