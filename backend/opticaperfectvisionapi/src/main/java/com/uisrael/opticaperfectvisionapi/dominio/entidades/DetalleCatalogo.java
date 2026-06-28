package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.CatalogoEntity;

public class DetalleCatalogo {

	private Integer idDetalleCatalogo;
	private CatalogoEntity  catalogo;
	private String nombre;
	private String identificador;
	private boolean estado;

	public DetalleCatalogo() {

	}

	public DetalleCatalogo(Integer idDetalleCatalogo, CatalogoEntity catalogo, String nombre, String identificador,
			boolean estado) {
		super();
		this.idDetalleCatalogo = idDetalleCatalogo;
		this.catalogo = catalogo;
		this.nombre = nombre;
		this.identificador = identificador;
		this.estado = estado;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
