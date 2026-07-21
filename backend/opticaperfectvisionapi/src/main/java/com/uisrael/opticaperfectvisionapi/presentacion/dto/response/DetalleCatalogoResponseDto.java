package com.uisrael.opticaperfectvisionapi.presentacion.dto.response;

public class DetalleCatalogoResponseDto {

	private Integer idDetalleCatalogo;
	private Integer idCatalogo;
	private String catalogoDescripcion;
	private String nombre;
	private String identificador;
	private boolean estado;

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

	public String getCatalogoDescripcion() {
		return catalogoDescripcion;
	}

	public void setCatalogoDescripcion(String catalogoDescripcion) {
		this.catalogoDescripcion = catalogoDescripcion;
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
