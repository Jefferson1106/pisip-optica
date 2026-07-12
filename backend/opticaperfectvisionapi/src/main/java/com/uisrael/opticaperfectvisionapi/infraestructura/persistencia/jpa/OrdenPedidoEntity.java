package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDate;
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
@Table(name = "orden_pedido")
@Check(constraints = "fecha_entrega >= fecha_pedido")
public class OrdenPedidoEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_examen", foreignKey = @ForeignKey(name = "fk_orden_pedido_examen_visual"))
    private ExamenVisualEntity examenVisual;

    @ManyToOne
    @JoinColumn(name = "id_paciente", foreignKey = @ForeignKey(name = "fk_orden_pedido_paciente"))
    private PacienteEntity paciente;

    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_estado_pedido", foreignKey = @ForeignKey(name = "fk_orden_pedido_estado_catalogo"))
    private DetalleCatalogoEntity estadoPedido;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
