package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenEntregaRequestDto {
	
	
	@NotNull
	@JsonAlias("ordenPedido")
    private Integer idPedido;
	@NotNull
    private LocalDate fechaEntrega;
	@NotNull
    private Boolean recibido;
	@NotNull
    private String observaciones;
	@NotNull
    private LocalDateTime fechaRegistro;
}
