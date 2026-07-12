package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;


import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenPedidoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenPedidoResponseDto;

@Mapper(componentModel = "spring")
public interface IOrdenPedidoDtoMapper {
	
	OrdenPedido toDomain(OrdenPedidoRequestDto dto);
	
	OrdenPedidoResponseDto toResponseDto(OrdenPedido ordenPedidoPojo);

}
