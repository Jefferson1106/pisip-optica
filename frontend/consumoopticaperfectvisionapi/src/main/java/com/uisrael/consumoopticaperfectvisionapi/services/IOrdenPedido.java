package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenPedidoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;

public interface IOrdenPedido {
	
	List<OrdenPedidoResponseDto> listarOrdenPedido();
	void guardarOrdenPedido(OrdenPedidoRequestDto nuevoOrdenPedido);
	void actualizarOdenPedido(OrdenPedidoRequestDto ordenPedido);
	OrdenPedidoResponseDto buscarPorId(Long id);
	
}
