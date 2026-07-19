package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PacienteRequestDto {

	private Long idPaciente; // <-- agrega este campo
	
	private String cedula;

	private String nombres;

	private String apellidos;

	private String direccion;

	private String telefono;

	private String correo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	private LocalDate fechaRegistro;

	@JsonAlias({ "idUsuario", "idUsuarioAdministrador", "id_usuario_registro" })
	private Integer idUsuarioRegistro;

	private Boolean activo;

}
