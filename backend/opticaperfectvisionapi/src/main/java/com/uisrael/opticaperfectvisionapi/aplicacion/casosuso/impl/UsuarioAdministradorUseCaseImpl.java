package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IUsuarioAdministradorUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.excepciones.CredencialesInvalidasException;
import com.uisrael.opticaperfectvisionapi.aplicacion.excepciones.UsuarioBloqueadoException;
import com.uisrael.opticaperfectvisionapi.aplicacion.excepciones.UsuarioNoEncontradoException;
import com.uisrael.opticaperfectvisionapi.aplicacion.servicios.IEnvioCorreoService;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IUsuarioAdministradorRepositorio;

public class UsuarioAdministradorUseCaseImpl implements IUsuarioAdministradorUseCase {

	private static final int MAX_INTENTOS_FALLIDOS = 5;

	private final IUsuarioAdministradorRepositorio repositorio;
	private final IEnvioCorreoService envioCorreoService;

	public UsuarioAdministradorUseCaseImpl(IUsuarioAdministradorRepositorio repositorio,
			IEnvioCorreoService envioCorreoService) {
		this.repositorio = repositorio;
		this.envioCorreoService = envioCorreoService;
	}

	@Override
	public UsuarioAdministrador guardar(UsuarioAdministrador usuarioAdministrador) {
		if (repositorio.existeCorreo(usuarioAdministrador.getCorreo().trim())) {
			throw new RuntimeException("Ya existe un usuario con ese correo");
		}
		usuarioAdministrador.setIntentosFallidos(0);
		return repositorio.guardar(usuarioAdministrador);
	}

	@Override
	public UsuarioAdministrador buscarPorId(Integer idUsuario) {
		return repositorio.buscarPorId(idUsuario)
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario administrador no encontrado"));
	}

	@Override
	public List<UsuarioAdministrador> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public UsuarioAdministrador actualizar(Integer idUsuario, UsuarioAdministrador usuarioAdministrador) {
		repositorio.buscarPorId(idUsuario)
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario administrador no encontrado"));

		usuarioAdministrador.setIdUsuario(idUsuario);
		return repositorio.actualizar(idUsuario, usuarioAdministrador);
	}

	@Override
	public UsuarioAdministrador actualizarEstado(Integer idUsuario, boolean estado) {
		repositorio.buscarPorId(idUsuario)
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario administrador no encontrado"));
		return repositorio.actualizarEstado(idUsuario, estado);
	}

	@Override
	public UsuarioAdministrador login(String correo, String contrasenia) {
		UsuarioAdministrador usuario = repositorio.buscarPorCorreo(correo.trim())
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario administrador no encontrado"));

		if (!usuario.isEstado()) {
			throw new UsuarioBloqueadoException("El usuario se encuentra bloqueado");
		}

		if (!usuario.getContrasenia().equals(contrasenia)) {
			int nuevosIntentos = usuario.getIntentosFallidos() + 1;
			usuario.setIntentosFallidos(nuevosIntentos);
			repositorio.actualizarIntentosFallidos(usuario.getIdUsuario(), nuevosIntentos);

			if (nuevosIntentos >= MAX_INTENTOS_FALLIDOS) {
				repositorio.actualizarEstado(usuario.getIdUsuario(), false);
				throw new UsuarioBloqueadoException("Usuario bloqueado por exceder 5 intentos fallidos");
			}

			throw new CredencialesInvalidasException("Contraseña incorrecta. Intento " + nuevosIntentos + " de " + MAX_INTENTOS_FALLIDOS);
		}

		if (usuario.getIntentosFallidos() > 0) {
			repositorio.actualizarIntentosFallidos(usuario.getIdUsuario(), 0);
			usuario.setIntentosFallidos(0);
		}

		return usuario;
	}

	@Override
	public void recuperarContrasenia(String correo) {
		UsuarioAdministrador usuario = repositorio.buscarPorCorreo(correo.trim())
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario administrador no encontrado"));
		envioCorreoService.enviarContrasenia(usuario.getCorreo(), usuario.getContrasenia());
		repositorio.actualizarEstado(usuario.getIdUsuario(), true);
	}
}