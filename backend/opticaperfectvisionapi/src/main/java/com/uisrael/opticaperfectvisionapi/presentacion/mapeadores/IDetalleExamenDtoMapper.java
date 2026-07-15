package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleExamenRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleExamenResponseDto;

@Mapper(componentModel = "spring")
public interface IDetalleExamenDtoMapper {
	
	@Mapping(target = "idDetExamen", ignore = true)
	@Mapping(target = "examenVisual", expression = "java(mapExamenVisual(detalleOrdenDto.getExamenVisual()))")
	DetalleExamen toDomain(DetalleExamenRequestDto detalleOrdenDto);

	@Mapping(target = "examenVisual", expression = "java(mapExamenVisualRef(detalleExamenPojo.getExamenVisual()))")
	DetalleExamenResponseDto toResponseDto(DetalleExamen detalleExamenPojo);

	default ExamenVisualEntity mapExamenVisual(DetalleExamenRequestDto.ExamenVisualRef examenVisualRef) {
		if (examenVisualRef == null || examenVisualRef.getIdExamen() == null) {
			return null;
		}
		ExamenVisualEntity examenVisual = new ExamenVisualEntity();
		examenVisual.setIdExamen(examenVisualRef.getIdExamen());
		return examenVisual;
	}

	default DetalleExamenResponseDto.ExamenVisualRef mapExamenVisualRef(ExamenVisualEntity examenVisual) {
		if (examenVisual == null || examenVisual.getIdExamen() == null) {
			return null;
		}
		DetalleExamenResponseDto.ExamenVisualRef examenVisualRef = new DetalleExamenResponseDto.ExamenVisualRef();
		examenVisualRef.setIdExamen(examenVisual.getIdExamen());
		return examenVisualRef;
	}
}
