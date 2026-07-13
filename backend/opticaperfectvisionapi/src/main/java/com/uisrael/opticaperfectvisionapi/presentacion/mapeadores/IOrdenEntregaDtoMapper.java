package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenEntregaResponseDto;

@Mapper(componentModel = "spring")
public interface IOrdenEntregaDtoMapper {

    @Mapping(target = "idEntrega", ignore = true)
    @Mapping(target = "ordenPedido", expression = "java(mapOrdenPedido(dto.getIdPedido()))")
    OrdenEntrega toDomain(OrdenEntregaRequestDto dto);

    @Mapping(target = "idPedido", source = "ordenPedido.idPedido")
    OrdenEntregaResponseDto toResponseDto(OrdenEntrega entity);
    
    default OrdenPedidoEntity mapOrdenPedido(Integer idPedido) {
        if (idPedido == null) {
            return null;
        }
        OrdenPedidoEntity ordenPedido = new OrdenPedidoEntity();
        ordenPedido.setIdPedido(idPedido);
        return ordenPedido;
    }
}
