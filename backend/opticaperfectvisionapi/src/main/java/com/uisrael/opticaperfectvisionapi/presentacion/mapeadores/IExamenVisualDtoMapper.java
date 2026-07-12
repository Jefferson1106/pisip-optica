package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.ExamenVisualRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.ExamenVisualResponseDto;

@Mapper(componentModel = "spring")
public interface IExamenVisualDtoMapper {

	@Mapping(target = "paciente.idPaciente", source = "idPaciente")
	ExamenVisual toDomain(ExamenVisualRequestDto dto);

	@Mapping(target = "idPaciente", source = "paciente.idPaciente")
	ExamenVisualResponseDto toResponseDto(ExamenVisual examenVisualPojo);
}
