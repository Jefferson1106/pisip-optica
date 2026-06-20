package com.uisrael.opticaperfectvisionapi.dominio.entidades;

public class Paciente {
	private Integer idUsuario;
	private Integer cedula;
    private String nombre;
    private String paciente;
    private String direccion;
    private String telefono;
    private String correo;
    
	public Paciente(Integer idUsuario, Integer cedula, String nombre, String paciente, String direccion,
			String telefono, String correo) {
		super();
		this.idUsuario = idUsuario;
		this.cedula = cedula;
		this.nombre = nombre;
		this.paciente = paciente;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	
	
	public Paciente() {
		super();
	}



	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getCedula() {
		return cedula;
	}
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
    
    
}
