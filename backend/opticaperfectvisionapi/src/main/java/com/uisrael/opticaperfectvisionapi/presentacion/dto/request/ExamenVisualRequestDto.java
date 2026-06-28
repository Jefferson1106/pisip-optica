package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamenVisualRequestDto {

	@NotBlank
	private String cedulaPaciente;

	@NotNull
	private LocalDate fechaExamen;

	@NotBlank
	private String observaciones;

	@NotNull
	private Boolean estado;
}
