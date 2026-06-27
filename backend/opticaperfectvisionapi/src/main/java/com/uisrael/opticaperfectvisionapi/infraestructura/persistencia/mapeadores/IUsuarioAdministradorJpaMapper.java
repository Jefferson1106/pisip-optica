package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

@Mapper(componentModel = "spring")
public interface IUsuarioAdministradorJpaMapper {
	
	UsuarioAdministrador toDomain(UsuarioAdministradorEntity entity);
	
	UsuarioAdministradorEntity toEntity(UsuarioAdministrador usuarioAdministradorPojo);
	
}
