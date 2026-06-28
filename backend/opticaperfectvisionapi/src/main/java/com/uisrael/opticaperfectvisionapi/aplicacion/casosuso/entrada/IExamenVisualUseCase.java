package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;

public interface IExamenVisualUseCase {

	ExamenVisual guardar(ExamenVisual nuevoExamenVisual);

	ExamenVisual buscarPorId(int idExamen);

	List<ExamenVisual> listarTodos();

	ExamenVisual actualizar(int id, ExamenVisual examenVisual);

	ExamenVisual actualizarEstado(int id, boolean estado);
}
