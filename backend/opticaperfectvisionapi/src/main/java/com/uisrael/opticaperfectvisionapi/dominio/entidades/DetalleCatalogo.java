package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.CatalogoEntity;

public class DetalleCatalogo {

	private Integer idDetalleCatalogo;
	private CatalogoEntity  catalogo;
	private String nombre;
	private String identificador;
	private LocalDate fechaRegistro;

	public DetalleCatalogo() {

	}
	
	public DetalleCatalogo(Integer idDetalleCatalogo, CatalogoEntity catalogo, String nombre, String identificador,
			LocalDate fechaRegistro) {
		this.idDetalleCatalogo = idDetalleCatalogo;
		this.catalogo = catalogo;
		this.nombre = nombre;
		this.identificador = identificador;
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getIdDetalleCatalogo() {
		return idDetalleCatalogo;
	}
	public void setIdDetalleCatalogo(Integer idDetalleCatalogo) {
		this.idDetalleCatalogo = idDetalleCatalogo;
	}
	public CatalogoEntity getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(CatalogoEntity catalogo) {
		this.catalogo = catalogo;
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
