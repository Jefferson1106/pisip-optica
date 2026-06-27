package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;

public interface IUsuarioAdministradorRepositorio {
	
	UsuarioAdministrador guardar(UsuarioAdministrador nuevoUsuarioAdministrador);
	
	Optional<UsuarioAdministrador> buscarPorId(int idUsuarioAdministrador);
	
	List<UsuarioAdministrador> listarTodos();
	
}
