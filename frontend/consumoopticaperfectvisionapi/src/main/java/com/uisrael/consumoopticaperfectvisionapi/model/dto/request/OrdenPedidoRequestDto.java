package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class OrdenPedidoRequestDto {

	    private Integer idExamen;

	    private Integer idPaciente;

	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    private LocalDate fechaPedido;

	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    private LocalDate fechaEntrega;

	    private Integer idEstadoPedido;
	    
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime fechaRegistro;

}
