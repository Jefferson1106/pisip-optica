package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExamenVisualRequestDto {

	@NotNull
	private Integer idPaciente;

	@NotNull
	private LocalDate fechaExamen;

	@Size(max = 500, message = "Las observaciones no deben exceder 500 caracteres")
	private String observaciones;

	@NotNull
	private Boolean estado;
}
