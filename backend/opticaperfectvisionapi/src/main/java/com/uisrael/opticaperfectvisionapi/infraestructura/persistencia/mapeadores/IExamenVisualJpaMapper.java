package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;

@Mapper (componentModel = "spring")
public interface IExamenVisualJpaMapper {
	ExamenVisual toDomain (ExamenVisualEntity entity);
	ExamenVisualEntity toEntity (ExamenVisual examenVisualPojo);

}
