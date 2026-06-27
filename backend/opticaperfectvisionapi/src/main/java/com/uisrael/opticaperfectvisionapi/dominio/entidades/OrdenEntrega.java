package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;

public class OrdenEntrega {
	
	private Integer idEntrega;
    private OrdenPedidoEntity ordenPedido;
    private LocalDate fechaEntrega;
    private Boolean recibido;
    private String observaciones;
    private LocalDateTime fechaRegistro;
    
	public OrdenEntrega() {

	}

	public OrdenEntrega(Integer idEntrega, OrdenPedidoEntity ordenPedido, LocalDate fechaEntrega, Boolean recibido,
			String observaciones, LocalDateTime fechaRegistro) {
		this.idEntrega = idEntrega;
		this.ordenPedido = ordenPedido;
		this.fechaEntrega = fechaEntrega;
		this.recibido = recibido;
		this.observaciones = observaciones;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(Integer idEntrega) {
		this.idEntrega = idEntrega;
	}

	public OrdenPedidoEntity getOrdenPedido() {
		return ordenPedido;
	}

	public void setOrdenPedido(OrdenPedidoEntity ordenPedido) {
		this.ordenPedido = ordenPedido;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Boolean getRecibido() {
		return recibido;
	}

	public void setRecibido(Boolean recibido) {
		this.recibido = recibido;
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
