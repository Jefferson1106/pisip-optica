package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "paciente")
public class PacienteEntity {
	
	@Id
	private Integer idUsuario;
	@Column(name = "cedula", length = 13)
	private Integer cedula;
	@Column(name = "nombre", length = 50)
    private String nombre;
	@Column(name = "paciente", length = 50)
    private String paciente;
	@Column(name = "direccion", length = 100)
    private String direccion;
	@Column(name = "telefono", length = 10)
    private String telefono;
	@Column(name = "correo", length = 50)
    private String correo;
	@Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
	@Column(name = "fechaRegistro")
    private LocalDateTime fechaRegistro;
    
}
