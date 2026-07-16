package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;

public interface IPacienteRepositorio {
	
	Paciente guardar(Paciente nuevoPaciente);
	
	Optional<Paciente> buscarPorId(int idPaciente);
	
	List<Paciente> listarTodos();
	void eliminar(String cedula);

	
	
	

}