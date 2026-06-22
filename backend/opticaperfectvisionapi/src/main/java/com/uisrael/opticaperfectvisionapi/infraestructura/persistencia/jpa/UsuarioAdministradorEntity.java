package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario_administrador")

public class UsuarioAdministradorEntity {
	@Id
	private Integer idUsuario;
	@Column (name = "detalle_catalogo")
	private Integer idDetalleCatalogo;
	@Column (name = "nombres", length = 100)
	private String nombres;
	@Column (name = "apellidos",length = 100)
	private String apellidos;
	@Column (name = "correo", length = 150)
	private String correo;
	@Column (name = "contrasenia", length = 255)
	private String contrasenia;
	@Column (name = "estado", length = 10)
	private String estado;
	private LocalDate fechaCreacion;


}
