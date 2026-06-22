package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;


public class DetalleOrden {
	
	private Integer idDetOrden;
	private String material;
	private String marco;
	private String tipoLente;
	private String tratamiento;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private LocalDate fechaRegistro;
	
	public DetalleOrden(Integer idDetOrden, String material, String marco, String tipoLente, String tratamiento,
			Integer cantidad, BigDecimal precioUnitario, LocalDate fechaRegistro) {
		super();
		this.idDetOrden = idDetOrden;
		this.material = material;
		this.marco = marco;
		this.tipoLente = tipoLente;
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

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMarco() {
		return marco;
	}

	public void setMarco(String marco) {
		this.marco = marco;
	}

	public String getTipoLente() {
		return tipoLente;
	}

	public void setTipoLente(String tipoLente) {
		this.tipoLente = tipoLente;
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
