package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

public class Paciente {

    private Integer idPaciente;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private Boolean activo;
    private UsuarioAdministradorEntity usuarioAdministrador;

    public Paciente() {
    }

    public Paciente(Integer idPaciente, String cedula, String nombres, String apellidos, String direccion,
                    String telefono, String correo, LocalDate fechaNacimiento,
                    LocalDateTime fechaRegistro, Boolean activo,
                    UsuarioAdministradorEntity usuarioAdministrador) {
        this.idPaciente = idPaciente;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.usuarioAdministrador = usuarioAdministrador;
    }

    public Paciente(Integer idPaciente2, String cedula2, UsuarioAdministradorEntity usuarioAdministrador2, String nombres2,
            String apellidos2, String direccion2, String telefono2, String correo2, LocalDate fechaNacimiento2,
            LocalDateTime fechaRegistro2, Boolean activo2) {
		// TODO Auto-generated constructor stub
	}

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

	public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

	public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public UsuarioAdministradorEntity getUsuarioAdministrador() {
        return usuarioAdministrador;
    }

    public void setUsuarioAdministrador(UsuarioAdministradorEntity usuarioAdministrador) {
        this.usuarioAdministrador = usuarioAdministrador;
    }
}
