package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrdenEntregaResponseDto {
	
	private Integer idEntrega;
	private Integer idPedido;
    private LocalDate fechaEntrega;
    private Boolean recibido;
    private String observaciones;
    private LocalDateTime fechaRegistro;
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