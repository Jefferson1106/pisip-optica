package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleExamenEntity;

@Mapper (componentModel = "spring")
public interface IDetalleExamenJpaMapper {
	DetalleExamen toDomain (DetalleExamenEntity entity);
	DetalleExamenEntity toEntity (DetalleExamen detalleExamenPojo);
	
}
