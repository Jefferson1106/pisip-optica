package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

public class OrdenPedido {
	
    private Integer idPedido;
    private ExamenVisualEntity examenVisual;
    private PacienteEntity paciente;
    private LocalDate fechaPedido;
    private LocalDate fechaEntrega;
    private String estado;
    private LocalDateTime fechaRegistro;
    
	public OrdenPedido() {
		
	}

	public OrdenPedido(Integer idPedido, ExamenVisualEntity examenVisual, PacienteEntity paciente,
			LocalDate fechaPedido, LocalDate fechaEntrega, String estado, LocalDateTime fechaRegistro) {
		this.idPedido = idPedido;
		this.examenVisual = examenVisual;
		this.paciente = paciente;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public ExamenVisualEntity getExamenVisual() {
		return examenVisual;
	}

	public void setExamenVisual(ExamenVisualEntity examenVisual) {
		this.examenVisual = examenVisual;
	}

	public PacienteEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteEntity paciente) {
		this.paciente = paciente;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
