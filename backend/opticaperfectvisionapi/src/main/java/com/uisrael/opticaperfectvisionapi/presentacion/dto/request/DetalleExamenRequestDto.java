package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.math.BigDecimal;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetalleExamenRequestDto {
		
		@NotBlank
	 	private Integer idDetExamen;
		@NotNull
	  	private ExamenVisualEntity examenVisual;
		@NotNull
	    private BigDecimal esferaDistanciaOd;
		@NotNull
	    private BigDecimal cilindroDistanciaOd;
		@NotNull
	    private Integer ejeDistanciaOd;
		@NotNull
	    private BigDecimal esferaDistanciaOi;
		@NotNull
	    private BigDecimal cilindroDistanciaOi;
		@NotNull
	    private Integer ejeDistanciaOi;
		@NotNull
	    private BigDecimal adicionOd;
		@NotNull
	    private BigDecimal adicionOi;
		@NotNull
	    private BigDecimal distanciaPupilar;
		@NotNull
	    private BigDecimal alturaBifocal;
		@NotNull
	    private BigDecimal alturaProgresivo;
		@NotNull
	    private BigDecimal esferaLecturaOd;
		@NotNull
	    private BigDecimal cilindroLecturaOd;
		@NotNull
	    private Integer ejeLecturaOd;
		@NotNull
	    private BigDecimal esferaLecturaOi;
		@NotNull
	    private BigDecimal cilindroLecturaOi;
		@NotNull
	    private Integer ejeLecturaOi;


}
