package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(
	name = "detalle_catalogo",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_detalle_catalogo_catalogo_identificador", columnNames = {"id_catalogo", "identificador"})
	}
)
public class DetalleCatalogoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_catalogo")
	private Integer idDetalleCatalogo;
	
	@ManyToOne
	@JoinColumn(name = "id_catalogo", foreignKey = @ForeignKey(name = "fk_detalle_catalogo_catalogo"))
	private CatalogoEntity  catalogo;
	
	@Column(name = "nombre", length = 100)
	private String nombre;

	@Column(name = "identificador", length = 100)
	private String identificador;

	@Column(name = "estado")
	private boolean estado;
}
