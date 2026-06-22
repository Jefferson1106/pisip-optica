package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;

public class DetalleCatalogo {
	private Integer idDetalleCatalogo;
	private Integer idCatalogo;
	private String nombre;
	private String identificador;
	private LocalDate fechaRegistro;
	
	public DetalleCatalogo(Integer idDetalleCatalogo,Integer idCatalogo, String nombre, String identificador, LocalDate fechaRegistro) {
		super();
		this.idDetalleCatalogo = idDetalleCatalogo;
		this.idCatalogo = idCatalogo;
		this.nombre = nombre;
		this.identificador = identificador;
		this.fechaRegistro = fechaRegistro;
	}
	public DetalleCatalogo() {
		super();
	}
	public Integer getIdDetalleCatalogo() {
		return idDetalleCatalogo;
	}
	public void setIdDetalleCatalogo(Integer idDetalleCatalogo) {
		this.idDetalleCatalogo = idDetalleCatalogo;
	}
	public Integer getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}
