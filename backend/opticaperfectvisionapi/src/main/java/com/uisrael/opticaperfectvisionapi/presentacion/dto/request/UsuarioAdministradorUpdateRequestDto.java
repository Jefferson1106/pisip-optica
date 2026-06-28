package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UsuarioAdministradorUpdateRequestDto {

	private Integer idTipoUsuario;
	private String nombres;
	private String apellidos;

	@Email
	private String correo;

	private String contrasenia;
	private Boolean estado;
}
