package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

public class ExamenVisual {

    private Integer idExamen;
    private PacienteEntity paciente;
    private LocalDate fechaExamen;
    private String observaciones;
    private LocalDateTime fechaRegistro;
    
	public ExamenVisual() {

	}

	public ExamenVisual(Integer idExamen, PacienteEntity paciente, LocalDate fechaExamen, String observaciones,
			LocalDateTime fechaRegistro) {
		this.idExamen = idExamen;
		this.paciente = paciente;
		this.fechaExamen = fechaExamen;
		this.observaciones = observaciones;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}

	public PacienteEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteEntity paciente) {
		this.paciente = paciente;
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

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}



