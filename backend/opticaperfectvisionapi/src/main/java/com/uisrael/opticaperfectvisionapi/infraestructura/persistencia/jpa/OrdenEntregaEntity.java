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
@Table(name = "orden_entrega")
public class OrdenEntregaEntity {
	@Id
	private Integer idEntrega;
	@Column(name = "idPedido", length = 10)
	private Integer idPedido;
	@Column(name = "fechaEntrega")
	private Date fechaEntrega;
	@Column (name="recibido")
	private Boolean recibido;
	@Column (name="observaciones", length = 100)
	private String observaciones;
	@Column(name="fechaRegistro")
	private LocalDateTime fechaRegistro;
	
}
