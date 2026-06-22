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
	@Column (name="idPedido")
	private Integer idPedido;
	@Column (name="idMaterial")
	private Integer idMaterial;
	@Column (name="idMarco")
	private Integer idMarco;
	@Column (name="idTipoLente")
	private Integer idTipoLente;
	@Column (name="tratamiento", length =100)
	private String tratamiento;
	@Column (name="cantidad")
	private Integer cantidad;
	@Column (name="precioUnitario", precision = 5, scale =2)
	private BigDecimal precioUnitario;
	@Column (name="fechaRegistro")
	private LocalDate fechaRegistro;
	
	
	

}
