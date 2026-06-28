package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioAdministradorRequestDto {

	@NotNull
	private Integer idTipoUsuario;

	@NotBlank
	private String nombres;

	@NotBlank
	private String apellidos;

	@NotBlank
	@Email
	private String correo;

	@NotBlank
	private String contrasenia;

	@NotNull
	private Boolean estado;
}