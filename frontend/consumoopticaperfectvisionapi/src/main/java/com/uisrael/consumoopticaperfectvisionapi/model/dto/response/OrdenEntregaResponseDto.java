package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data


public class OrdenEntregaResponseDto {
	
	private Integer idEntrega;
	private Integer idPedido;
    private LocalDate fechaEntrega;
    private Boolean recibido;
    private String observaciones;
    private LocalDateTime fechaRegistro;

   
}