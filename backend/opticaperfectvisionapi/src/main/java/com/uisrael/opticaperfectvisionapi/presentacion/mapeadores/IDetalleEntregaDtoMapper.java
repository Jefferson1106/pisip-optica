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
    @Mapping(target = "ordenEntrega", expression = "java(mapOrdenEntrega(dto.getIdEntrega()))")
    @Mapping(target = "producto", expression = "java(mapProducto(dto.getIdProducto()))")
    DetalleEntrega toDomain(DetalleEntregaRequestDto dto);

    @Mapping(target = "idEntrega", source = "ordenEntrega.idEntrega")
    @Mapping(target = "idProducto", source = "producto.idDetOrden") 
    DetalleEntregaResponseDto toResponseDto(DetalleEntrega entity);

    default OrdenEntregaEntity mapOrdenEntrega(Integer idEntrega) {
        if (idEntrega == null) {
            return null;
        }
        OrdenEntregaEntity ordenEntrega = new OrdenEntregaEntity();
       ordenEntrega.setIdEntrega(idEntrega);
        return ordenEntrega;
    }

    default DetalleOrdenEntity mapProducto(Integer idProducto) {
        if (idProducto == null) {
            return null;
        }
        DetalleOrdenEntity producto = new DetalleOrdenEntity();
        producto.setIdDetOrden(idProducto);
        return producto;
    }
}