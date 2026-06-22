package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "detalle_catalogo")
public class DetalleCatalogoEntity {
	
	@Id
	private Integer idDetalleCatalogo;
	@Column (name = "id_catalogo")
	private Integer idCatalogo;
	@Column (name = "nombre",length = 100)
	private String nombre;
	@Column (name = "identificador", length = 100)
	private String identificador;
	private LocalDate fechaRegistro;
}
