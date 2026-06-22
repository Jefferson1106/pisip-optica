package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;

public class DetalleEntrega {

	private Integer idDetEntrega;
    private OrdenEntregaEntity ordenEntrega;
    private DetalleOrdenEntity producto;
    private Integer cantidad;
    private Boolean estado;
    private LocalDateTime fechaRegistro;
    
	public DetalleEntrega() {

	}

	public DetalleEntrega(Integer idDetEntrega, OrdenEntregaEntity ordenEntrega, DetalleOrdenEntity producto,
			Integer cantidad, Boolean estado, LocalDateTime fechaRegistro) {
		super();
		this.idDetEntrega = idDetEntrega;
		this.ordenEntrega = ordenEntrega;
		this.producto = producto;
		this.cantidad = cantidad;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdDetEntrega() {
		return idDetEntrega;
	}

	public void setIdDetEntrega(Integer idDetEntrega) {
		this.idDetEntrega = idDetEntrega;
	}

	public OrdenEntregaEntity getOrdenEntrega() {
		return ordenEntrega;
	}

	public void setOrdenEntrega(OrdenEntregaEntity ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}

	public DetalleOrdenEntity getProducto() {
		return producto;
	}

	public void setProducto(DetalleOrdenEntity producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
