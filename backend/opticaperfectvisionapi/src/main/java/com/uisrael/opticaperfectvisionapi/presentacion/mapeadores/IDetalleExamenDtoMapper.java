package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleExamenRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleExamenResponseDto;

@Mapper(componentModel = "spring")
public interface IDetalleExamenDtoMapper {
	
	DetalleExamen toDomain(DetalleExamenRequestDto dto);
	
	DetalleExamenResponseDto toResponseDto(DetalleExamen detalleExamenPojo);

}
