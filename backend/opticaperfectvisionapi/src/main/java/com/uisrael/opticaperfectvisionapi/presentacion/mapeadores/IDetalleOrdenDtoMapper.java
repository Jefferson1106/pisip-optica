package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleOrdenRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleOrdenResponseDto;

@Mapper(componentModel = "spring")
public interface IDetalleOrdenDtoMapper {
	
	@Mapping(target = "idDetOrden", ignore = true)
	@Mapping(target = "ordenPedido.idPedido", source = "idPedido")
	@Mapping(target = "material.idDetalleCatalogo", source = "idMaterial")
	@Mapping(target = "marco.idDetalleCatalogo", source = "idMarco")
	@Mapping(target = "tipoLente.idDetalleCatalogo", source = "idTipoLente")
	DetalleOrden toDomain(DetalleOrdenRequestDto dto);
	
	@Mapping(target = "idPedido", source = "ordenPedido.idPedido")
	@Mapping(target = "idMaterial", source = "material.idDetalleCatalogo")
	@Mapping(target = "idMarco", source = "marco.idDetalleCatalogo")
	@Mapping(target = "idTipoLente", source = "tipoLente.idDetalleCatalogo")
	DetalleOrdenResponseDto toResponseDto(DetalleOrden detalleOrdenPojo);


}
