package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleCatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;

public interface IDetalleCatalogoService {

	List<DetalleCatalogoResponseDto> listarDetalleCatalogos();

	DetalleCatalogoResponseDto buscarPorId(Integer idDetalleCatalogo);

	void guardarDetalleCatalogo(DetalleCatalogoRequestDto nuevoDetalleCatalogo);

	void actualizarDetalleCatalogo(Integer idDetalleCatalogo, DetalleCatalogoRequestDto detalleCatalogo);
}