package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.PacienteResponseDto;

public interface IPacienteDtoMapper {
	
	Paciente toDomain(PacienteRequestDto dto);

	PacienteResponseDto toResponseDto(Paciente pacientePojo);


}
