package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;

public class DetalleEntrega {
	private Integer idDetalleEntrega;
	private Integer cantidad;
	private boolean estado;
	private LocalDate fechaRegistro;
	
	public DetalleEntrega(Integer idDetalleEntrega, Integer cantidad, boolean estado, LocalDate fechaRegistro) {
		super();
		this.idDetalleEntrega = idDetalleEntrega;
		this.cantidad = cantidad;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}
	public DetalleEntrega() {
		super();
	}
	public Integer getIdDetalleEntrega() {
		return idDetalleEntrega;
	}
	public void setIdDetalleEntrega(Integer idDetalleEntrega) {
		this.idDetalleEntrega = idDetalleEntrega;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}
