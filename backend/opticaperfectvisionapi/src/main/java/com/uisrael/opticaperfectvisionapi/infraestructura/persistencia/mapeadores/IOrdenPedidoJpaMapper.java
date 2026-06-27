package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;

@Mapper(componentModel = "spring")
public interface IOrdenPedidoJpaMapper {
		
	OrdenPedido toDomain(OrdenPedidoEntity entity);
	
	OrdenPedidoEntity toEntity(OrdenPedido ordenPedidoPojo);

}
