package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;


import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;

public interface IUsuarioAdministradorUseCase {

	UsuarioAdministrador guardar(UsuarioAdministrador nuevoUsuarioAdministrador);
	
	UsuarioAdministrador buscarPorId(int idUsuarioAdministrador);
	
	List<UsuarioAdministrador> listarTodos();
}
