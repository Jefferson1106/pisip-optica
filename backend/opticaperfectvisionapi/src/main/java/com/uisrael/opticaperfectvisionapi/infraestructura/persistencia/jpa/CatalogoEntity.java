package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "catalogo")
public class CatalogoEntity {
	@Id
	private Integer idCatalogo;
	
	@Column(name = "descripcion", length = 255)
    private String descripcion;
    private LocalDateTime fechaRegistro;
}
