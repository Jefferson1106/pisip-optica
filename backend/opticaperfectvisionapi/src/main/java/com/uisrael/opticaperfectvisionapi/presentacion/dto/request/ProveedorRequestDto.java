package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProveedorRequestDto {
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "La identificación es obligatoria")
	@Pattern(regexp = "^\\d{10}(\\d{3})?$",
			message = "La identificación debe contener 10 dígitos para cédula o 13 para RUC")
	private String identificacion;

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "Ingrese un correo electrónico válido")
	private String correo;

	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(regexp = "^\\d{10}$", message = "El teléfono debe contener exactamente 10 números")
	private String telefono;
	private String direccion;
	private boolean estado = true;

	@AssertTrue(message = "La cédula o RUC ingresado no es válido")
	public boolean isIdentificacionEcuatorianaValida() {
		if (identificacion == null || !identificacion.matches("\\d{10}|\\d{13}")) {
			return true;
		}
		return identificacion.length() == 10
				? validarCedula(identificacion)
				: validarRuc(identificacion);
	}

	private boolean validarRuc(String valor) {
		int tercerDigito = Character.getNumericValue(valor.charAt(2));
		if (tercerDigito < 6) {
			return validarCedula(valor.substring(0, 10)) && valor.endsWith("001");
		}
		if (tercerDigito == 6) {
			return validarModulo11(valor, new int[] {3, 2, 7, 6, 5, 4, 3, 2}, 8)
					&& valor.substring(9).equals("0001");
		}
		if (tercerDigito == 9) {
			return validarModulo11(valor, new int[] {4, 3, 2, 7, 6, 5, 4, 3, 2}, 9)
					&& valor.endsWith("001");
		}
		return false;
	}

	private boolean validarCedula(String valor) {
		int provincia = Integer.parseInt(valor.substring(0, 2));
		if (provincia < 1 || provincia > 24 || Character.getNumericValue(valor.charAt(2)) >= 6) {
			return false;
		}
		int suma = 0;
		for (int i = 0; i < 9; i++) {
			int digito = Character.getNumericValue(valor.charAt(i));
			if (i % 2 == 0) {
				digito *= 2;
				if (digito > 9) digito -= 9;
			}
			suma += digito;
		}
		return (10 - suma % 10) % 10 == Character.getNumericValue(valor.charAt(9));
	}

	private boolean validarModulo11(String valor, int[] pesos, int posicionVerificador) {
		int suma = 0;
		for (int i = 0; i < pesos.length; i++) {
			suma += Character.getNumericValue(valor.charAt(i)) * pesos[i];
		}
		int verificador = 11 - suma % 11;
		if (verificador == 11) verificador = 0;
		if (verificador == 10) return false;
		return verificador == Character.getNumericValue(valor.charAt(posicionVerificador));
	}
}
