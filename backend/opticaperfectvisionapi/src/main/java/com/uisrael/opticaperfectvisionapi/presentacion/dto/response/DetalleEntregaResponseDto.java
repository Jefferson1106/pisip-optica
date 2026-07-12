package com.uisrael.opticaperfectvisionapi.presentacion.dto.response;
import java.time.LocalDateTime;

public class DetalleEntregaResponseDto {
	private Integer idDetEntrega;
    private Integer cantidad;
    private Boolean estado;
    private LocalDateTime fechaRegistro;
    private Integer idEntrega;
    private Integer idProducto;
    
	public Integer getIdDetEntrega() {
		return idDetEntrega;
	}
	public void setIdDetEntrega(Integer idDetEntrega) {
		this.idDetEntrega = idDetEntrega;
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
	public Integer getIdEntrega() {
		return idEntrega;
	}
	public void setIdEntrega(Integer idEntrega) {
		this.idEntrega = idEntrega;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
    

	   

}
