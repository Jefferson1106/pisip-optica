package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioAdministradorLoginRequestDto {

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "Debe ingresar un correo valido")
	private String correo;

	@NotBlank(message = "La contrasenia es obligatoria")
	private String contrasenia;
}