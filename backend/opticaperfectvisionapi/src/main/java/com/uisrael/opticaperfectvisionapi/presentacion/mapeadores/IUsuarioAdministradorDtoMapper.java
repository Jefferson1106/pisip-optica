package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.UsuarioAdministradorLoginRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.UsuarioAdministradorRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.LoginResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.UsuarioAdministradorResponseDto;

@Mapper(componentModel = "spring")
public interface IUsuarioAdministradorDtoMapper {

	@Mapping(target = "tipoUsuario.idDetalleCatalogo", source = "idTipoUsuario")
	UsuarioAdministrador toDomain(UsuarioAdministradorRequestDto dto);

	UsuarioAdministrador toDomain(UsuarioAdministradorLoginRequestDto dto);

	@Mapping(target = "idTipoUsuario", source = "tipoUsuario.idDetalleCatalogo")
	@Mapping(target = "tipoUsuarioNombre", source = "tipoUsuario.nombre")
	UsuarioAdministradorResponseDto toResponseDto(UsuarioAdministrador usuarioAdministrador);

	LoginResponseDto toLoginResponseDto(UsuarioAdministrador usuarioAdministrador);
}