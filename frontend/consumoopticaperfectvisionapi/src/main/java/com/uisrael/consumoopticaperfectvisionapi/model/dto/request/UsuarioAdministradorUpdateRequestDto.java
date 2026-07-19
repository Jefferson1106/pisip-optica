package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import lombok.Data;

@Data
public class UsuarioAdministradorUpdateRequestDto {

	private Integer idTipoUsuario;
	private String nombres;
	private String apellidos;

	private String correo;

	private String contrasenia;
	private Boolean estado;
}
