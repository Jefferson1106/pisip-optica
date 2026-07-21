package com.uisrael.opticaperfectvisionapi.presentacion.mapeadores;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenPedidoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenPedidoResponseDto;

@Mapper(componentModel = "spring")
public interface IOrdenPedidoDtoMapper {
	
	@Mapping(target = "idPedido", ignore = true)
	@Mapping(target = "examenVisual.idExamen", source = "idExamen")
	@Mapping(target = "paciente.idPaciente", source = "idPaciente")
	@Mapping(target = "estadoPedido.idDetalleCatalogo", source = "idEstadoPedido")
	OrdenPedido toDomain(OrdenPedidoRequestDto dto);
	
	@Mapping(target = "idExamen", source = "examenVisual.idExamen")
	@Mapping(target = "idPaciente", source = "paciente.idPaciente")
	@Mapping(target = "pacienteNombre", expression = "java(ordenPedidoPojo.getPaciente() != null ? ((ordenPedidoPojo.getPaciente().getNombres() + \" \" + ordenPedidoPojo.getPaciente().getApellidos()).trim()) : null)")
	@Mapping(target = "examenDescripcion", expression = "java(ordenPedidoPojo.getExamenVisual() != null ? (\"Examen #\" + ordenPedidoPojo.getExamenVisual().getIdExamen() + \" | \" + (ordenPedidoPojo.getExamenVisual().getPaciente() != null ? ((ordenPedidoPojo.getExamenVisual().getPaciente().getNombres() + \" \" + ordenPedidoPojo.getExamenVisual().getPaciente().getApellidos()).trim()) : \"\") + \" | \" + ordenPedidoPojo.getExamenVisual().getFechaExamen()) : null)")
	@Mapping(target = "idEstadoPedido", source = "estadoPedido.idDetalleCatalogo")
	@Mapping(target = "nombreEstadoPedido", source = "estadoPedido.nombre")
	@Mapping(target = "identificadorEstadoPedido", source = "estadoPedido.identificador")
	OrdenPedidoResponseDto toResponseDto(OrdenPedido ordenPedidoPojo);

}
