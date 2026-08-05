package com.uisrael.opticaperfectvisionapi.presentacion.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalleOrdenResponseDto {
	
	private Integer idDetOrden;
	private Integer idPedido;
	private Integer idProducto;
	private String productoNombre;
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
	public Integer getIdProducto() { return idProducto; }
	public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
	public String getProductoNombre() { return productoNombre; }
	public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
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
