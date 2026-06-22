package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "detalle_catalogo")
public class DetalleCatalogoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_catalogo")
	private Integer idDetalleCatalogo;
	
	@ManyToOne
    @JoinColumn(name = "idcatalogo")
	private CatalogoEntity  catalogo;
	
	@Column(name = "nombre", length = 100)
	private String nombre;

	@Column(name = "identificador", length = 100)
	private String identificador;

	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
}
