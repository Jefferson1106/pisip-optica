package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;



public class UsuarioAdministrador {
	
    private Integer idUsuario;
    private DetalleCatalogoEntity tipoUsuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasenia;
    private boolean estado;
    private int intentosFallidos = 0;

    public UsuarioAdministrador() {

	}

	public UsuarioAdministrador(Integer idUsuario, DetalleCatalogoEntity tipoUsuario, String nombres, String apellidos,
			String correo, String contrasenia, boolean estado, int intentosFallidos) {
		super();
		this.idUsuario = idUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.intentosFallidos = intentosFallidos;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public DetalleCatalogoEntity getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(DetalleCatalogoEntity tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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
