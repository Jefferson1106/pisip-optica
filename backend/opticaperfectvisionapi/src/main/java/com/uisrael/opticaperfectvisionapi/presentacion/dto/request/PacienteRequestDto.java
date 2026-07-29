package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;
//import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PacienteRequestDto {

		@NotBlank(message = "La cédula es obligatoria")
		@Pattern(regexp = "^\\d{10}$", message = "La cédula debe tener exactamente 10 números")
		private String cedula;
	   
		
		@NotBlank(message = "Los nombres son obligatorios")
		@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
				message = "Los nombres solo deben contener letras y espacios")
	    private String nombres;
		@NotBlank(message = "Los apellidos son obligatorios")
		@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
				message = "Los apellidos solo deben contener letras y espacios")
	    private String apellidos;
		@NotBlank
	    private String direccion;
		@NotBlank(message = "El teléfono es obligatorio")
		@Pattern(regexp = "^\\d{7,20}$", message = "El teléfono solo debe contener entre 7 y 20 números")
	    private String telefono;
		@Pattern(regexp = "^$|^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Correo invalido")
	    private String correo;
		@NotNull
	    private LocalDate fechaNacimiento;
		private Boolean activo;
		
		//1907
		
		//@NotNull
		@JsonFormat(pattern = "yyyy-MM-dd['T'HH:mm[:ss][.SSS]]")
	    private LocalDateTime fechaRegistro;
		//@NotNull
		@JsonAlias({ "idUsuario", "idUsuarioAdministrador", "id_usuario_registro" })
		private Integer idUsuarioRegistro;
		
		
		


	

	    

}
