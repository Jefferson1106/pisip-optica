package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class OrdenPedidoRequestDto {
		
		@NotNull
	    private Integer idExamen;
		@NotNull
	    private Integer idPaciente;
		@NotNull
	    private LocalDate fechaPedido;
		@NotNull
	    private LocalDate fechaEntrega;
		@NotNull
	    private Integer idEstadoPedido;
		@NotNull
	    private LocalDateTime fechaRegistro;

}
