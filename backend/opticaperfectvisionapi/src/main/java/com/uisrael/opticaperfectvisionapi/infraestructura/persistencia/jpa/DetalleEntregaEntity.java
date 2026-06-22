package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table (name = "detalle_entrega")

public class DetalleEntregaEntity {
	@Id
	private Integer idDetalleEntrega;
	private Integer cantidad;
	private boolean estado;
	private LocalDate fechaRegistro;

}
