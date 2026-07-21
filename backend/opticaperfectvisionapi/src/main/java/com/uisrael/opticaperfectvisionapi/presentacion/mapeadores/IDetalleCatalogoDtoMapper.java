package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleCatalogoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleCatalogoResponseDto;

@Mapper(componentModel = "spring")
public interface IDetalleCatalogoDtoMapper {

	@Mapping(target = "catalogo.idCatalogo", source = "idCatalogo")
	DetalleCatalogo toDomain(DetalleCatalogoRequestDto dto);

	@Mapping(target = "idCatalogo", source = "catalogo.idCatalogo")
	@Mapping(target = "catalogoDescripcion", source = "catalogo.descripcion")
	DetalleCatalogoResponseDto toResponseDto(DetalleCatalogo detalleCatalogoPojo);
}
