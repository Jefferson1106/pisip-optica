package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioAdministradorRecuperacionRequestDto {

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "Debe ingresar un correo valido")
	private String correo;
}