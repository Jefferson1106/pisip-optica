package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleOrdenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;

public interface IDetalleOrden {
	
	List<DetalleOrdenResponseDto> listarDetalleOrden();
	void guardarDetalleOrden(DetalleOrdenRequestDto nuevoDetalleOrden);
	
}
