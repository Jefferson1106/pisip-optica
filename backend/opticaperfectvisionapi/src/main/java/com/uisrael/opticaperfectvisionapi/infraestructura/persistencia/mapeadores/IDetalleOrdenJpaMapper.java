package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;

@Mapper (componentModel = "spring")
public interface IDetalleOrdenJpaMapper {
	DetalleOrden toDomain (DetalleOrdenEntity entity);
	DetalleOrdenEntity toEntity (DetalleOrden detalleOrdenPojo);

}
