package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.PacienteResponseDto;


@Mapper(componentModel = "spring")
public interface IPacienteDtoMapper {

Paciente toDomain(PacienteRequestDto dto);
	
PacienteRequestDto toResponseDto(Paciente pacientePojo);


   
}
