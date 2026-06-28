package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleEntregaResponseDto;

public interface IDetalleEntregaDtoMapper {

	DetalleEntrega toDomain(DetalleEntregaRequestDto dto);

	DetalleEntregaResponseDto toResponseDto(DetalleEntrega detalleEntregaPojo);

}
