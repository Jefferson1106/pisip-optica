package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenEntregaResponseDto;

@Mapper(componentModel = "spring")
public interface IOrdenEntregaDtoMapper {

    @Mapping(target = "idEntrega", ignore = true)
    @Mapping(target = "ordenPedido", ignore = true) // se asigna en el UseCase
    OrdenEntrega toDomain(OrdenEntregaRequestDto dto);

    @Mapping(target = "idPedido", source = "ordenPedido.idPedido")
    OrdenEntregaResponseDto toResponseDto(OrdenEntrega entity);
}
