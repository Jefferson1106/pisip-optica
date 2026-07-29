package com.uisrael.consumoopticaperfectvisionapi.services;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorLoginRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorRecuperacionRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CambioContraseniaInicialRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.LoginResponseDto;

public interface IAuthService {

	LoginResponseDto login(UsuarioAdministradorLoginRequestDto credenciales);

	String recuperarContrasenia(UsuarioAdministradorRecuperacionRequestDto request);

	LoginResponseDto cambiarContraseniaInicial(Integer idUsuario, CambioContraseniaInicialRequestDto request);
}
