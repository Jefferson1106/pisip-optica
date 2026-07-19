package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleExamenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;

public interface IDetalleExamen {
	
	List<DetalleExamenResponseDto> listarDetalleExamen();
	void guardarDetalleExamen(DetalleExamenRequestDto nuevoDetalleExamen);

}
