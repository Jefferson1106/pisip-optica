package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioAdministradorLoginRequestDto {

	@NotBlank
	@Email
	private String correo;

	@NotBlank
	private String contrasenia;
}