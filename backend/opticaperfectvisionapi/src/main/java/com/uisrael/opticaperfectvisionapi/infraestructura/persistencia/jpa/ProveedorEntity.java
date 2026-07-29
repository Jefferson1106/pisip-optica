package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class ProveedorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private Integer idProveedor;

	@Column(nullable = false, unique = true, length = 150)
	private String nombre;

	@Column(nullable = false, unique = true, length = 13)
	private String identificacion;

	@Column(length = 150)
	private String correo;

	@Column(length = 30)
	private String telefono;

	@Column(length = 250)
	private String direccion;

	@Column(nullable = false)
	private boolean estado = true;
}
