package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrdenEntregaRequestDto {

	    private Integer idEntrega;
	
	    @JsonAlias("ordenPedido")
	    @NotNull(message = "Debe seleccionar un pedido")
	    private Integer idPedido;

	    @NotNull(message = "La fecha de entrega es obligatoria")
	    private LocalDate fechaEntrega;

	    @NotNull(message = "Debe indicar si la entrega fue recibida")
	    private Boolean recibido;

	    @Size(max = 500, message = "Las observaciones no deben exceder 500 caracteres")
	    private String observaciones;

	    @PastOrPresent(message = "La fecha de registro no puede ser futura")
	    private LocalDateTime fechaRegistro;
	}

