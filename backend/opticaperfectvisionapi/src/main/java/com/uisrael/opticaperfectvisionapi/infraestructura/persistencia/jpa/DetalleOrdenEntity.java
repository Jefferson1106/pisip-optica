package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="detalle_orden")
public class DetalleOrdenEntity {
	
	@Id
	private Integer idDetOrden;
	@Column (name="material", length =100)
	private String material;
	@Column (name="marco", length =100)
	private String marco;
	@Column (name="tipoLente", length =100)
	private String tipoLente;
	@Column (name="tratamiento", length =100)
	private String tratamiento;
	@Column (name="cantidad")
	private Integer cantidad;
	@Column (name="precioUnitario", precision = 5, scale =2)
	private BigDecimal precioUnitario;
	@Column (name="fechaRegistro")
	private LocalDate fechaRegistro;
	
	
	

}
