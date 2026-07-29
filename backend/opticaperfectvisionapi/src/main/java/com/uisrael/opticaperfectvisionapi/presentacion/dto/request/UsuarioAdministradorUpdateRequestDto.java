package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UsuarioAdministradorUpdateRequestDto {

	private Integer idTipoUsuario;
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los nombres solo deben contener letras y espacios")
	private String nombres;
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los apellidos solo deben contener letras y espacios")
	private String apellidos;

	@Email(message = "La estructura del correo no es válida")
	private String correo;

	@Pattern(
			regexp = "^$|^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
			message = "La estructura de la contraseña no es válida")
	private String contrasenia;
	private Boolean estado;
}
