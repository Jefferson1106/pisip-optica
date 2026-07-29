package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class OrdenPedidoRequestDto {

	    private Integer idPedido;

	    @NotNull(message = "Debe seleccionar un examen")
	    private Integer idExamen;

	    @NotNull(message = "Debe seleccionar un paciente")
	    private Integer idPaciente;

	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    @NotNull(message = "La fecha de pedido es obligatoria")
	    private LocalDate fechaPedido;

	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    @NotNull(message = "La fecha de entrega es obligatoria")
	    private LocalDate fechaEntrega;

	    @NotNull(message = "Debe seleccionar un estado de pedido")
	    private Integer idEstadoPedido;
	    
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime fechaRegistro;

}
