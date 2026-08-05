package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
@Check(constraints = "cantidad > 0 and precio_unitario >= 0")
public class DetalleOrdenEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_orden")
    private Integer idDetOrden;

    @ManyToOne
    @JoinColumn(name = "id_pedido", foreignKey = @ForeignKey(name = "fk_detalle_orden_pedido"))
    private OrdenPedidoEntity ordenPedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto", nullable = false,
            foreignKey = @ForeignKey(name = "fk_detalle_orden_producto"))
    private ProductoEntity producto;

    @Column(name = "tratamiento", length = 100)
    private String tratamiento;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
