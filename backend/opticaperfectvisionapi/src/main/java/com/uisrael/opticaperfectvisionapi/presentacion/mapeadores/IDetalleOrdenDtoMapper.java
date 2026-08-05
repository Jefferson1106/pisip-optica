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
	@Mapping(target = "producto.idProducto", source = "idProducto")
	DetalleOrden toDomain(DetalleOrdenRequestDto dto);
	
	@Mapping(target = "idPedido", source = "ordenPedido.idPedido")
	@Mapping(target = "idProducto", source = "producto.idProducto")
	@Mapping(target = "productoNombre", source = "producto.nombre")
	DetalleOrdenResponseDto toResponseDto(DetalleOrden detalleOrdenPojo);


}
