package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.CatalogoResponseDto;

public interface ICatalogoService {

	List<CatalogoResponseDto> listarCatalogos();

	CatalogoResponseDto buscarPorId(Integer idCatalogo);

	void guardarCatalogo(CatalogoRequestDto nuevoCatalogo);

	void actualizarCatalogo(Integer idCatalogo, CatalogoRequestDto catalogo);
}