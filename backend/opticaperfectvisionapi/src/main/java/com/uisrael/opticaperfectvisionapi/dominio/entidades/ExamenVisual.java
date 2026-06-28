package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

public class ExamenVisual {

    private Integer idExamen;
    private PacienteEntity paciente;
    private LocalDate fechaExamen;
    private String observaciones;
    private boolean estado;
    
	public ExamenVisual() {

	}

	public ExamenVisual(Integer idExamen, PacienteEntity paciente, LocalDate fechaExamen, String observaciones,
			boolean estado) {
		this.idExamen = idExamen;
		this.paciente = paciente;
		this.fechaExamen = fechaExamen;
		this.observaciones = observaciones;
		this.estado = estado;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


}



