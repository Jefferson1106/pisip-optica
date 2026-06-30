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
		
	  	private ExamenVisualEntity examenVisual;
		
	    private BigDecimal esferaDistanciaOd;
		
	    private BigDecimal cilindroDistanciaOd;
		
	    private Integer ejeDistanciaOd;
		
	    private BigDecimal esferaDistanciaOi;
		
	    private BigDecimal cilindroDistanciaOi;
		
	    private Integer ejeDistanciaOi;
		
	    private BigDecimal adicionOd;
		
	    private BigDecimal adicionOi;
		
	    private BigDecimal distanciaPupilar;
		
	    private BigDecimal alturaBifocal;
		
	    private BigDecimal alturaProgresivo;
		
	    private BigDecimal esferaLecturaOd;
		
	    private BigDecimal cilindroLecturaOd;
		
	    private Integer ejeLecturaOd;
		
	    private BigDecimal esferaLecturaOi;
		
	    private BigDecimal cilindroLecturaOi;
		
	    private Integer ejeLecturaOi;


}
