package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

@Mapper(componentModel = "spring")
public interface IPacienteJpaMapper {
	
	Paciente toDomain(PacienteEntity entity);
	
	PacienteEntity toEntity(Paciente pacientePojo);
	
	
}
