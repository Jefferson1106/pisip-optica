package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleEntregaResponseDto;

@Mapper(componentModel = "spring")
public interface IDetalleEntregaDtoMapper {

    @Mapping(target = "idDetEntrega", ignore = true)
    DetalleEntrega toDomain(DetalleEntregaRequestDto dto);

  
    DetalleEntregaResponseDto toResponseDto(DetalleEntrega detalleEntregaPojo);
}
