package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;

public interface IExamenVisualRepositorio {

	ExamenVisual guardar(ExamenVisual nuevoExamenVisual);

	Optional<ExamenVisual> buscarPorId(int idExamen);

	List<ExamenVisual> listarTodos();

	List<ExamenVisual> listarPorPaciente(int idPaciente);

	ExamenVisual actualizar(int id, ExamenVisual examenVisual);

	ExamenVisual actualizarEstado(int id, ExamenVisual examenVisual);
}
