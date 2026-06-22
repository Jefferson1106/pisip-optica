package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name="detalle_orden")
public class DetalleOrdenEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_orden")
    private Integer idDetOrden;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private OrdenPedidoEntity ordenPedido;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private DetalleCatalogoEntity material;

    @ManyToOne
    @JoinColumn(name = "id_marco")
    private DetalleCatalogoEntity marco;

    @ManyToOne
    @JoinColumn(name = "id_tipo_lente")
    private DetalleCatalogoEntity tipoLente;

    @Column(name = "tratamiento", length = 100)
    private String tratamiento;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
