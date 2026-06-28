package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IPacienteRepositorio;

public class PacienteUseCaseImpl implements IPacienteUseCase {
	private final IPacienteRepositorio repositorio;

	public PacienteUseCaseImpl(IPacienteRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}

	@Override
	public Paciente guardar(Paciente nuevoPaciente) {
		return repositorio.guardar(nuevoPaciente);
	}

	@Override
	public Paciente buscarPorId(int idPaciente) {
		return repositorio.buscarPorId(idPaciente)
				.orElseThrow(()-> new RuntimeException("Grupo no encntrado"));

	}

	@Override
	public List<Paciente> listarTodos() {
		return repositorio.listarTodos();
	}
	
	

}
