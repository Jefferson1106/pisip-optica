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
    @Mapping(target = "ordenEntrega", expression = "java(new OrdenEntregaEntity(dto.getIdEntrega()))")
    @Mapping(target = "producto", expression = "java(new DetalleOrdenEntity(dto.getIdProducto()))")
    DetalleEntrega toDomain(DetalleEntregaRequestDto dto);

    @Mapping(target = "idEntrega", source = "ordenEntrega.idEntrega")
    @Mapping(target = "idProducto", source = "producto.idDetOrden") 
    DetalleEntregaResponseDto toResponseDto(DetalleEntrega entity);
}
