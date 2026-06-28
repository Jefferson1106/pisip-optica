package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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
		@NotBlank
	    private LocalDateTime fechaNacimiento;
		@NotBlank
	    private LocalDateTime fechaRegistro;
	    

}
