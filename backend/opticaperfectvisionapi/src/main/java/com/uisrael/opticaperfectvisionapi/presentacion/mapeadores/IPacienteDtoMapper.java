package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

@Mapper(componentModel = "spring")
public interface IPacienteDtoMapper {

    @Mapping(target = "usuarioAdministrador", expression = "java(mapUsuario(dto.getIdUsuarioRegistro()))")
    PacienteEntity toEntity(PacienteRequestDto dto);


}
