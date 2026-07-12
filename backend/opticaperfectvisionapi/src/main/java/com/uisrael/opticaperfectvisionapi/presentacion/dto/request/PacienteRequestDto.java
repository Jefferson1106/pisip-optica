package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteRequestDto {
	   
		
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
	    private LocalDateTime fechaNacimiento;
		@NotNull
	    private LocalDateTime fechaRegistro;
		@NotNull
		private Integer idUsuario;
	    

}
