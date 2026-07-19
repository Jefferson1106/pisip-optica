package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DetalleExamenRequestDto {

		private ExamenVisualRef examenVisual;
		
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

		
		public boolean isIdExamenValido() {
			return examenVisual != null && examenVisual.getIdExamen() != null;
		}

		@Data
		public static class ExamenVisualRef {
			
			private Integer idExamen;
		}


}
