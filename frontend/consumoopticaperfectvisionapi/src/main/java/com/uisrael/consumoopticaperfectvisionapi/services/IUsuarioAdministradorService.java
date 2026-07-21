package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorUpdateRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.UsuarioAdministradorResponseDto;

public interface IUsuarioAdministradorService {

	List<UsuarioAdministradorResponseDto> listarUsuarios();

	UsuarioAdministradorResponseDto buscarPorId(Integer idUsuario);

	void guardarUsuario(UsuarioAdministradorRequestDto usuario);

	void actualizarUsuario(Integer idUsuario, UsuarioAdministradorUpdateRequestDto usuario);
}