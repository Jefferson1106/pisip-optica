package com.uisrael.opticaperfectvisionapi.presentacion.dto.response;

public class UsuarioAdministradorResponseDto {

	private Integer idUsuario;
	private Integer idTipoUsuario;
	private String tipoUsuarioNombre;
	private String nombres;
	private String apellidos;
	private String correo;
	private boolean estado;
	private int intentosFallidos;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getTipoUsuarioNombre() {
		return tipoUsuarioNombre;
	}

	public void setTipoUsuarioNombre(String tipoUsuarioNombre) {
		this.tipoUsuarioNombre = tipoUsuarioNombre;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}
}