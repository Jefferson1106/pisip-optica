package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa;

import java.math.BigDecimal;

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
@Table(name = "detalle_examen")
@Check(constraints = "eje_distancia_od between 0 and 180 and eje_distancia_oi between 0 and 180 and eje_lectura_od between 0 and 180 and eje_lectura_oi between 0 and 180")
public class DetalleExamenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_examen")
    private Integer idDetExamen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_examen", foreignKey = @ForeignKey(name = "fk_detalle_examen_examen_visual"))
    private ExamenVisualEntity examenVisual;

    @Column(name = "esfera_distancia_od", precision = 5, scale = 2)
    private BigDecimal esferaDistanciaOd;

    @Column(name = "cilindro_distancia_od", precision = 5, scale = 2)
    private BigDecimal cilindroDistanciaOd;

    @Column(name = "eje_distancia_od")
    private Integer ejeDistanciaOd;

    @Column(name = "esfera_distancia_oi", precision = 5, scale = 2)
    private BigDecimal esferaDistanciaOi;

    @Column(name = "cilindro_distancia_oi", precision = 5, scale = 2)
    private BigDecimal cilindroDistanciaOi;

    @Column(name = "eje_distancia_oi")
    private Integer ejeDistanciaOi;

    @Column(name = "adicion_od", precision = 5, scale = 2)
    private BigDecimal adicionOd;

    @Column(name = "adicion_oi", precision = 5, scale = 2)
    private BigDecimal adicionOi;

    @Column(name = "distancia_pupilar", precision = 5, scale = 2)
    private BigDecimal distanciaPupilar;

    @Column(name = "altura_bifocal", precision = 5, scale = 2)
    private BigDecimal alturaBifocal;

    @Column(name = "altura_progresivo", precision = 5, scale = 2)
    private BigDecimal alturaProgresivo;

    @Column(name = "esfera_lectura_od", precision = 5, scale = 2)
    private BigDecimal esferaLecturaOd;

    @Column(name = "cilindro_lectura_od", precision = 5, scale = 2)
    private BigDecimal cilindroLecturaOd;

    @Column(name = "eje_lectura_od")
    private Integer ejeLecturaOd;

    @Column(name = "esfera_lectura_oi", precision = 5, scale = 2)
    private BigDecimal esferaLecturaOi;

    @Column(name = "cilindro_lectura_oi", precision = 5, scale = 2)
    private BigDecimal cilindroLecturaOi;

    @Column(name = "eje_lectura_oi")
    private Integer ejeLecturaOi;
}
