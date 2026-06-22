package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;

public class UsuarioAdministrador {
	
	private Integer idUsuario;
	private Integer idDetalleCatalogo;
	private String nombres;
	private String apellidos;
	private String correo;
	private String contrasenia;
	private String estado;
	private LocalDate fechaCreacion;
	public UsuarioAdministrador(Integer idUsuario,Integer idDetalleCatalogo, String nombres, String apellidos, String correo, String contrasenia,
			String estado, LocalDate fechaCreacion) {
		super();
		this.idUsuario = idUsuario;
		this.idDetalleCatalogo = idDetalleCatalogo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
	}
	public UsuarioAdministrador() {
		super();
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdDetalleCatalogo() {
		return idDetalleCatalogo;
	}
	public void setIdDetalleCatalogo(Integer idDetalleCatalogo) {
		this.idDetalleCatalogo = idDetalleCatalogo;	
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


}
