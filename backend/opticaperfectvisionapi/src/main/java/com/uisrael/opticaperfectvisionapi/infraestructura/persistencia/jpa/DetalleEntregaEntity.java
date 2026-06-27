package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDateTime;

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
@Table (name = "detalle_entrega")

public class DetalleEntregaEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_entrega")
    private Integer idDetEntrega;

    @ManyToOne
    @JoinColumn(name = "id_entrega")
    private OrdenEntregaEntity ordenEntrega;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private DetalleOrdenEntity producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

}
