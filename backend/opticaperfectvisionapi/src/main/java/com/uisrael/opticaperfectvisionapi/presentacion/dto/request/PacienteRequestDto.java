package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;
//import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteRequestDto {

		@NotBlank
		private String cedula;
	   
		
		@NotBlank
	    private String nombres;
		@NotBlank
	    private String apellidos;
		@NotBlank
	    private String direccion;
		@NotBlank
	    private String telefono;
		@NotBlank
	    private String correo;
		@NotNull
	    private LocalDate fechaNacimiento;
		private Boolean activo;
		
		//1907
		
		//@NotNull
	    private LocalDateTime fechaRegistro;
		//@NotNull
		@JsonAlias({ "idUsuario", "idUsuarioAdministrador", "id_usuario_registro" })
		private Integer idUsuarioRegistro;
		
		
		


	

	    

}
