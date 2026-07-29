package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UsuarioAdministradorRequestDto {

	@NotNull
	private Integer idTipoUsuario;

	@NotBlank(message = "Los nombres son obligatorios")
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los nombres solo deben contener letras y espacios")
	private String nombres;

	@NotBlank(message = "Los apellidos son obligatorios")
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los apellidos solo deben contener letras y espacios")
	private String apellidos;

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "La estructura del correo no es válida")
	private String correo;

	@NotBlank(message = "La contraseña es obligatoria")
	@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
			message = "La estructura de la contraseña no es válida")
	private String contrasenia;

	@NotNull
	private Boolean estado;
}
