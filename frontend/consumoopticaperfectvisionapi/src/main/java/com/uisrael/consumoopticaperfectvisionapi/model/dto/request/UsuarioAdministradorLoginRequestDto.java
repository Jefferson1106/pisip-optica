package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import lombok.Data;

@Data
public class UsuarioAdministradorLoginRequestDto {

	private String correo;

	private String contrasenia;
}