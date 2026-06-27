package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.CatalogoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.CatalogoResponseDto;

@Mapper(componentModel = "spring")
public interface ICatalogoDtoMapper {
	Catalogo toDomain(CatalogoRequestDto dto);
	
	CatalogoResponseDto toResponseDto(Catalogo catalogoPojo);
}
