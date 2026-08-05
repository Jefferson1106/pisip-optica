package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
public class DetalleExamenRequestDto {

		private ExamenVisualRef examenVisual;
		
	    private BigDecimal esferaDistanciaOd;
		
	    private BigDecimal cilindroDistanciaOd;
		
	    @Min(value = 0, message = "El eje de distancia OD debe estar entre 0 y 180 grados")
	    @Max(value = 180, message = "El eje de distancia OD debe estar entre 0 y 180 grados")
	    private Integer ejeDistanciaOd;
		
	    private BigDecimal esferaDistanciaOi;
		
	    private BigDecimal cilindroDistanciaOi;
		
	    @Min(value = 0, message = "El eje de distancia OI debe estar entre 0 y 180 grados")
	    @Max(value = 180, message = "El eje de distancia OI debe estar entre 0 y 180 grados")
	    private Integer ejeDistanciaOi;
		
	    private BigDecimal adicionOd;
		
	    private BigDecimal adicionOi;
		
	    private BigDecimal distanciaPupilar;
		
	    private BigDecimal alturaBifocal;
		
	    private BigDecimal alturaProgresivo;
		
	    private BigDecimal esferaLecturaOd;
		
	    private BigDecimal cilindroLecturaOd;
		
	    @Min(value = 0, message = "El eje de lectura OD debe estar entre 0 y 180 grados")
	    @Max(value = 180, message = "El eje de lectura OD debe estar entre 0 y 180 grados")
	    private Integer ejeLecturaOd;
		
	    private BigDecimal esferaLecturaOi;
		
	    private BigDecimal cilindroLecturaOi;
		
	    @Min(value = 0, message = "El eje de lectura OI debe estar entre 0 y 180 grados")
	    @Max(value = 180, message = "El eje de lectura OI debe estar entre 0 y 180 grados")
	    private Integer ejeLecturaOi;

		
		public boolean isIdExamenValido() {
			return examenVisual != null && examenVisual.getIdExamen() != null;
		}

		@Data
		public static class ExamenVisualRef {
			
			private Integer idExamen;
		}


}
