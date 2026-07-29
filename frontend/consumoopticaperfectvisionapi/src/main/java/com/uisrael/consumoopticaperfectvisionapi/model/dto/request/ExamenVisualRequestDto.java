package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExamenVisualRequestDto {

	private Integer idExamen;

	@NotNull(message = "Debe seleccionar un paciente")
	private Integer idPaciente;

	@NotNull(message = "La fecha de examen es obligatoria")
	@PastOrPresent(message = "La fecha de examen no puede ser futura")
	private LocalDate fechaExamen;

	@Size(max = 500, message = "Las observaciones no deben exceder 500 caracteres")
	private String observaciones;

	@NotNull(message = "Debe indicar si el examen esta activo")
	private Boolean estado;
}
