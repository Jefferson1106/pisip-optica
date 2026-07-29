package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioAdministradorUpdateRequestDto {

	@NotNull(message = "Debe seleccionar un tipo de usuario")
	private Integer idTipoUsuario;

	@NotBlank(message = "Los nombres son obligatorios")
	@Size(max = 100, message = "Los nombres no deben exceder 100 caracteres")
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los nombres solo deben contener letras y espacios")
	private String nombres;

	@NotBlank(message = "Los apellidos son obligatorios")
	@Size(max = 100, message = "Los apellidos no deben exceder 100 caracteres")
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los apellidos solo deben contener letras y espacios")
	private String apellidos;

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "La estructura del correo no es válida. Ejemplo: usuario@dominio.com")
	private String correo;

	@Pattern(
			regexp = "^$|^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
			message = "La estructura de la contraseña no es válida: debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial")
	private String contrasenia;

	@NotNull(message = "Debe indicar si el usuario esta activo")
	private Boolean estado;
}
