package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class PacienteRequestDto {

		private String cedula;

	  	private String nombres;
	    private String apellidos;
	    private String direccion;
	    private String telefono;
	    private String correo;
	    private LocalDate fechaNacimiento;
	    private LocalDateTime fechaRegistro;
		@JsonAlias({ "idUsuario", "idUsuarioAdministrador", "id_usuario_registro" })
		private Integer idUsuarioRegistro;


	    private String nombres;

	    private String apellidos;

	    private String direccion;

	    private String telefono;

	    private String correo;

	    private LocalDate fechaNacimiento;

	    private LocalDateTime fechaRegistro;

		@JsonAlias({ "idUsuario", "idUsuarioAdministrador", "id_usuario_registro" })
		private Integer idUsuarioRegistro;


		private Boolean activo;
	

	    

}
