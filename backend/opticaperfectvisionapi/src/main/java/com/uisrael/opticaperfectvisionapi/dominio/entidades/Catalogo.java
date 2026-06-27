package com.uisrael.opticaperfectvisionapi.dominio.entidades;

public class Catalogo {

    private Integer idCatalogo;
    private String descripcion;
    private boolean estado;
    
	public Catalogo() {

	}

	public Catalogo(Integer idCatalogo, String descripcion, boolean estado) {
		this.idCatalogo = idCatalogo;
		this.descripcion = descripcion;
		this.estado = estado;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}