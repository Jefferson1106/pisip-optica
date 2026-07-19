package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private Boolean activo;


}
