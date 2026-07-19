package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PacienteResponseDto {
	
	private Integer idPaciente;
	private String cedula;
	    private Integer idUsuarioRegistro;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String correo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

	  
    private LocalDate fechaRegistro;
    private Boolean activo;

    

}
