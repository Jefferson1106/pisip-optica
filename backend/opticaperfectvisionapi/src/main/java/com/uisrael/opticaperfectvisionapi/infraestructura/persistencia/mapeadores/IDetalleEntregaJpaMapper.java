package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleEntregaEntity;

@Mapper (componentModel = "spring")
public interface IDetalleEntregaJpaMapper {
	DetalleEntrega toDomain(DetalleEntregaEntity entity);
	DetalleEntregaEntity toEntity (DetalleEntrega detalleEntregaPojo);

}
