package com.uisrael.opticaperfectvisionapi.presentacion.dto.response;

import java.time.LocalDate;

public class ExamenVisualResponseDto {

	private Integer idExamen;
	private Integer idPaciente;
	private LocalDate fechaExamen;
	private String observaciones;
	private boolean estado;

	public Integer getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public LocalDate getFechaExamen() {
		return fechaExamen;
	}

	public void setFechaExamen(LocalDate fechaExamen) {
		this.fechaExamen = fechaExamen;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
