package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenEntregaResponseDto;

public interface OrdenEntregaDtoMapper {
	
	OrdenEntrega toDomain(OrdenEntregaRequestDto dto);

	OrdenEntregaResponseDto toResponseDto(OrdenEntrega ordenEntregaPojo);


}
