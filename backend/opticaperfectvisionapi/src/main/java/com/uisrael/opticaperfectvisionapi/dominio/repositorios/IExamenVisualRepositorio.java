package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;

public interface IExamenVisualRepositorio {
	ExamenVisual guardar(ExamenVisual nuevoExamenVisual);
	Optional<ExamenVisual> buscarPorId(int idExamenVisual);
	List<ExamenVisual> listarTodos();
	void eliminar (int idExamenVisual);

}
