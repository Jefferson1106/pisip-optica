package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

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
@Table(name = "orden_pedido")
public class OrdenPedidoEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_examen")
    private ExamenVisualEntity examenVisual;

    @ManyToOne
    @JoinColumn(name = "cedula")
    private PacienteEntity paciente;

    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
