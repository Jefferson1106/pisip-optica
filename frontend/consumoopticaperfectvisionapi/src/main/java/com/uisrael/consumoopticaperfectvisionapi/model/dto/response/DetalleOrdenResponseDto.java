package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalleOrdenResponseDto {
	
	private Integer idDetOrden;
	private Integer idPedido;
	private Integer idMaterial;
	private Integer idMarco;
	private Integer idTipoLente;
	private String tratamiento;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private LocalDateTime fechaRegistro;
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
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    
    

}
