package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleExamenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;

public interface IDetalleExamen {
	
	List<DetalleExamenResponseDto> listarDetalleExamen();
	DetalleExamenResponseDto buscarDetalleExamenPorId(Integer idDetExamen);
	void guardarDetalleExamen(DetalleExamenRequestDto nuevoDetalleExamen);
	void actualizarDetalleExamen(Integer idDetExamen, DetalleExamenRequestDto detalleExamen);
	void eliminarDetalleExamen(Integer idDetExamen);

}
