package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ProductoEntity;

public class DetalleOrden {
	
	private Integer idDetOrden;
    private OrdenPedidoEntity ordenPedido;
    private ProductoEntity producto;
	private String tratamiento;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private LocalDateTime fechaRegistro;
    
	public DetalleOrden() {

	}

	public DetalleOrden(Integer idDetOrden, OrdenPedidoEntity ordenPedido, ProductoEntity producto,
			String tratamiento, Integer cantidad,
			BigDecimal precioUnitario, LocalDateTime fechaRegistro) {
		this.idDetOrden = idDetOrden;
		this.ordenPedido = ordenPedido;
		this.producto = producto;
		this.tratamiento = tratamiento;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdDetOrden() {
		return idDetOrden;
	}

	public void setIdDetOrden(Integer idDetOrden) {
		this.idDetOrden = idDetOrden;
	}

	public OrdenPedidoEntity getOrdenPedido() {
		return ordenPedido;
	}

	public void setOrdenPedido(OrdenPedidoEntity ordenPedido) {
		this.ordenPedido = ordenPedido;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
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

	public Object getEstado() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEstado(Object estado) {
		// TODO Auto-generated method stub
		
	}

	public Object getIdDetalleOrden() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setIdDetalleOrden(Object idDetalleOrden) {
		// TODO Auto-generated method stub
		
	}
}
