package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.ExamenVisualRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.ExamenVisualResponseDto;

@Mapper(componentModel = "spring")
public interface IExamenVisualDtoMapper {

	@Mapping(target = "paciente.cedula", source = "cedulaPaciente")
	ExamenVisual toDomain(ExamenVisualRequestDto dto);

	@Mapping(target = "cedulaPaciente", source = "paciente.cedula")
	ExamenVisualResponseDto toResponseDto(ExamenVisual examenVisualPojo);
}
