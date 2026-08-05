package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;

@Mapper (componentModel = "spring")
public interface IDetalleOrdenJpaMapper {
	@Mapping(target = "producto", source = "producto")
	DetalleOrden toDomain (DetalleOrdenEntity entity);

	@Mapping(target = "producto", source = "producto")
	DetalleOrdenEntity toEntity (DetalleOrden detalleOrdenPojo);

}
