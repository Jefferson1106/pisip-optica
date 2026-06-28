package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleOrdenRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleOrdenReponseDto;

@Mapper(componentModel = "spring")
public interface IDetalleOrdenDtoMapper {
	
	DetalleOrden toDomain(DetalleOrdenRequestDto dto);
	
	DetalleOrdenReponseDto toResponseDto(DetalleOrden detalleOrdenPojo);


}
