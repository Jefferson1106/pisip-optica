package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CambioContraseniaInicialRequestDto {

	@NotBlank(message = "La nueva contraseña es obligatoria")
	@Pattern(
			regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
			message = "Debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial")
	private String nuevaContrasenia;

	@NotBlank(message = "Debe confirmar la nueva contraseña")
	private String confirmarContrasenia;

	public String getNuevaContrasenia() {
		return nuevaContrasenia;
	}

	public void setNuevaContrasenia(String nuevaContrasenia) {
		this.nuevaContrasenia = nuevaContrasenia;
	}

	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}

	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
}
