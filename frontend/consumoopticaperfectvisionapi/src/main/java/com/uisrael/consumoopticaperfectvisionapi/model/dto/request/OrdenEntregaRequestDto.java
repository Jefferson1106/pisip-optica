package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class OrdenEntregaRequestDto {
	
	    @JsonAlias("ordenPedido")
	    private Integer idPedido;
	    private LocalDate fechaEntrega;
	    private Boolean recibido;
	    private String observaciones;
	    private LocalDateTime fechaRegistro;
	}

