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
	@Mapping(target = "pacienteNombre", expression = "java(examenVisualPojo.getPaciente() != null ? ((examenVisualPojo.getPaciente().getNombres() + \" \" + examenVisualPojo.getPaciente().getApellidos()).trim()) : null)")
	ExamenVisualResponseDto toResponseDto(ExamenVisual examenVisualPojo);
}
