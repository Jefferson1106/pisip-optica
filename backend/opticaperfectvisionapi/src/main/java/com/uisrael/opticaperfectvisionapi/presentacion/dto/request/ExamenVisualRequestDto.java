package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamenVisualRequestDto {

	@NotNull
	private Integer idPaciente;

	@NotNull
	private LocalDate fechaExamen;

	@NotBlank
	private String observaciones;

	@NotNull
	private Boolean estado;
}
