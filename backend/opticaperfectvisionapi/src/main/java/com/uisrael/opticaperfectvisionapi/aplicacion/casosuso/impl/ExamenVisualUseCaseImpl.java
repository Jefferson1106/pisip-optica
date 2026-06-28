package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IExamenVisualUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IExamenVisualRepositorio;

public class ExamenVisualUseCaseImpl implements IExamenVisualUseCase {

	private final IExamenVisualRepositorio repositorio;

	public ExamenVisualUseCaseImpl(IExamenVisualRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public ExamenVisual guardar(ExamenVisual nuevoExamenVisual) {
		return repositorio.guardar(nuevoExamenVisual);
	}

	@Override
	public ExamenVisual buscarPorId(int idExamen) {
		return repositorio.buscarPorId(idExamen)
				.orElseThrow(() -> new RuntimeException("Examen visual no encontrado"));
	}

	@Override
	public List<ExamenVisual> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public ExamenVisual actualizar(int id, ExamenVisual examenVisual) {
		repositorio.buscarPorId(id)
				.orElseThrow(() -> new RuntimeException("Examen visual no encontrado"));

		ExamenVisual actualizado = new ExamenVisual(
				id,
				examenVisual.getPaciente(),
				examenVisual.getFechaExamen(),
				examenVisual.getObservaciones(),
				examenVisual.isEstado());

		return repositorio.actualizar(id, actualizado);
	}

	@Override
	public ExamenVisual actualizarEstado(int id, boolean estado) {
		ExamenVisual actual = repositorio.buscarPorId(id)
				.orElseThrow(() -> new RuntimeException("Examen visual no encontrado"));

		ExamenVisual actualizado = new ExamenVisual(
				actual.getIdExamen(),
				actual.getPaciente(),
				actual.getFechaExamen(),
				actual.getObservaciones(),
				estado);

		return repositorio.actualizarEstado(id, actualizado);
	}
}
