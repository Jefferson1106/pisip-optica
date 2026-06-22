package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class DetalleOrden {
	
	private Integer idDetOrden;
	private Integer idPedido;
	private Integer idMaterial;
	private Integer idMarco;
	private Integer idTipoLente;
	private String tratamiento;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private LocalDate fechaRegistro;
	
	public DetalleOrden(Integer idDetOrden, Integer idPedido, Integer idMaterial, Integer idMarco, Integer idTipoLente,
			String tratamiento, Integer cantidad, BigDecimal precioUnitario, LocalDate fechaRegistro) {
		super();
		this.idDetOrden = idDetOrden;
		this.idPedido = idPedido;
		this.idMaterial = idMaterial;
		this.idMarco = idMarco;
		this.idTipoLente = idTipoLente;
		this.tratamiento = tratamiento;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.fechaRegistro = fechaRegistro;
	}

	public DetalleOrden() {
		super();
	}

	public Integer getIdDetOrden() {
		return idDetOrden;
	}

	public void setIdDetOrden(Integer idDetOrden) {
		this.idDetOrden = idDetOrden;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Integer getIdMarco() {
		return idMarco;
	}

	public void setIdMarco(Integer idMarco) {
		this.idMarco = idMarco;
	}

	public Integer getIdTipoLente() {
		return idTipoLente;
	}

	public void setIdTipoLente(Integer idTipoLente) {
		this.idTipoLente = idTipoLente;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	
}
