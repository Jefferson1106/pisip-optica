package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;

@Mapper(componentModel = "spring")
public interface IPacienteDtoMapper {

    // Al crear un paciente, usuarioAdministrador se asigna en el UseCase
    @Mapping(target = "usuarioAdministrador", ignore = true)
    PacienteEntity toEntity(PacienteRequestDto dto);

   
}
