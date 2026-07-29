package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PacienteRequestDto {

	private Long idPaciente; // <-- agrega este campo
	
	@NotBlank(message = "La cedula es obligatoria")
	@Pattern(regexp = "^\\d{10}$", message = "La cedula debe tener exactamente 10 digitos")
	private String cedula;

	@NotBlank(message = "Los nombres son obligatorios")
	@Size(max = 120, message = "Los nombres no deben exceder 120 caracteres")
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los nombres solo deben contener letras y espacios")
	private String nombres;

	@NotBlank(message = "Los apellidos son obligatorios")
	@Size(max = 120, message = "Los apellidos no deben exceder 120 caracteres")
	@Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)*$",
			message = "Los apellidos solo deben contener letras y espacios")
	private String apellidos;

	@NotBlank(message = "La direccion es obligatoria")
	@Size(max = 250, message = "La direccion no debe exceder 250 caracteres")
	private String direccion;

	@NotBlank(message = "El telefono es obligatorio")
	@Pattern(regexp = "^\\d{7,20}$", message = "El teléfono solo debe contener entre 7 y 20 números")
	private String telefono;

	@Pattern(regexp = "^$|^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Debe ingresar un correo valido")
	@Size(max = 150, message = "El correo no debe exceder 150 caracteres")
	private String correo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@Past(message = "La fecha de nacimiento debe ser anterior a hoy")
	private LocalDate fechaNacimiento;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaRegistro;

	@JsonAlias({ "idUsuario", "idUsuarioAdministrador", "id_usuario_registro" })
	private Integer idUsuarioRegistro;

	@NotNull(message = "Debe indicar si el paciente esta activo")
	private Boolean activo;

}
