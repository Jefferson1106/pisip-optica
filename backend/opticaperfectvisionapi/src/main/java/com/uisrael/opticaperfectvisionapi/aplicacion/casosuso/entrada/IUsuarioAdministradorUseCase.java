package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;

public interface IUsuarioAdministradorUseCase {

	UsuarioAdministrador guardar(UsuarioAdministrador usuarioAdministrador);

	UsuarioAdministrador buscarPorId(Integer idUsuario);

	List<UsuarioAdministrador> listarTodos();

	UsuarioAdministrador actualizar(Integer idUsuario, UsuarioAdministrador usuarioAdministrador);

	UsuarioAdministrador actualizarEstado(Integer idUsuario, boolean estado);

	UsuarioAdministrador login(String correo, String contrasenia);

	void recuperarContrasenia(String correo);
}