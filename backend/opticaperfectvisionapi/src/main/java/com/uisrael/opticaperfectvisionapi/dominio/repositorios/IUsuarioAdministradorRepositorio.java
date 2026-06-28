package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;

public interface IUsuarioAdministradorRepositorio {

	UsuarioAdministrador guardar(UsuarioAdministrador usuarioAdministrador);

	Optional<UsuarioAdministrador> buscarPorId(Integer idUsuario);

	Optional<UsuarioAdministrador> buscarPorCorreo(String correo);

	List<UsuarioAdministrador> listarTodos();

	UsuarioAdministrador actualizar(Integer idUsuario, UsuarioAdministrador usuarioAdministrador);

	UsuarioAdministrador actualizarEstado(Integer idUsuario, boolean estado);

	UsuarioAdministrador actualizarIntentosFallidos(Integer idUsuario, int intentosFallidos);

	boolean existeCorreo(String correo);

	boolean existeCorreoParaOtro(String correo, Integer idUsuario);
}