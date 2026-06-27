package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.CatalogoEntity;

@Mapper(componentModel = "spring")
public interface ICatalogoJpaMapper {
	Catalogo toDomain(CatalogoEntity entity);
	
	CatalogoEntity toEntity(Catalogo catalogoPojo);
}
