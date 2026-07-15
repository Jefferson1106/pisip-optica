package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.PacienteResponseDto;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

@Mapper(componentModel = "spring")
public interface IPacienteDtoMapper {

    @Mapping(target = "idPaciente", ignore = true)
    @Mapping(target = "usuarioAdministrador.idUsuario", source = "idUsuarioRegistro")
    Paciente toDomain(PacienteRequestDto dto);

    @Mapping(target = "idUsuarioRegistro", source = "usuarioAdministrador.idUsuario")
    PacienteResponseDto toResponseDto(Paciente paciente);


}
