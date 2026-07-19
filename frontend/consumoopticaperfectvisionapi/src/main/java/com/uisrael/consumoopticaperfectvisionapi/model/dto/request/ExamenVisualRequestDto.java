package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExamenVisualRequestDto {

	private Integer idPaciente;

	private LocalDate fechaExamen;

	private String observaciones;

	private Boolean estado;
}
