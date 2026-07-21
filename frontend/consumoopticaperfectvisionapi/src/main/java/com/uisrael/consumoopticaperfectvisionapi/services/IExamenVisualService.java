package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ExamenVisualRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;

public interface IExamenVisualService {

	List<ExamenVisualResponseDto> listarExamenesVisuales();

	ExamenVisualResponseDto buscarPorId(Integer idExamenVisual);

	void guardarExamenVisual(ExamenVisualRequestDto nuevoExamenVisual);

	void actualizarExamenVisual(Integer idExamenVisual, ExamenVisualRequestDto examenVisual);
}