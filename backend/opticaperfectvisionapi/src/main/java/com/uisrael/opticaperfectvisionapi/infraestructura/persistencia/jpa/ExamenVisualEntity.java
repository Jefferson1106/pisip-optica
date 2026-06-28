package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.time.LocalDate;

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
@Table(name = "examen_visual")
public class ExamenVisualEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen")
    private Integer idExamen;

    @ManyToOne
    @JoinColumn(name = "cedula")
    private PacienteEntity paciente;

    @Column(name = "fecha_examen")
    private LocalDate fechaExamen;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "estado")
    private boolean estado;
}
