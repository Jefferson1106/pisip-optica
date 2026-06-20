package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDateTime;

public class Catalogo {

    private Integer idCatalogo;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    
	public Catalogo(Integer idCatalogo, String descripcion, LocalDateTime fechaRegistro) {
		super();
		this.idCatalogo = idCatalogo;
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
	}
	
	public Catalogo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}   
}