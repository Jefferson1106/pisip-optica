package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;

@Mapper(componentModel = "spring")
public interface IOrdenEntregaJpaMapper {
	
	OrdenEntrega toDomain(OrdenEntregaEntity entity);
	
	OrdenEntregaEntity toEntity(OrdenEntrega ordenEntregaPojo);
}
