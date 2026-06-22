package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDateTime;
import java.util.Date;

public class OrdenEntrega {
	
	private Integer idEntrega;
	private Integer idPedido;
	private Date fechaEntrega;
	private Boolean recibido;
	private String observaciones;
	private LocalDateTime fechaRegistro;
	
	public OrdenEntrega(Integer idEntrega, Integer idPedido, Date fechaEntrega, Boolean recibido, String observaciones,
			LocalDateTime fechaRegistro) {
		super();
		this.idEntrega = idEntrega;
		this.idPedido = idPedido;
		this.fechaEntrega = fechaEntrega;
		this.recibido = recibido;
		this.observaciones = observaciones;
		this.fechaRegistro = fechaRegistro;
	}

	public OrdenEntrega() {
		super();
	}

	public Integer getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(Integer idEntrega) {
		this.idEntrega = idEntrega;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
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
