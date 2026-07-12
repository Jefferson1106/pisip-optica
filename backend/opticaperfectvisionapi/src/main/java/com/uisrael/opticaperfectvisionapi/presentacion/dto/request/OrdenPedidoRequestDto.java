package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class OrdenPedidoRequestDto {
		
		@NotBlank
	 	private Integer idPedido;
		@NotNull
	    private ExamenVisualEntity examenVisual;
		@NotNull
	    private PacienteEntity paciente;
		@NotNull
	    private LocalDate fechaPedido;
		@NotNull
	    private LocalDate fechaEntrega;
		@NotNull
	    private String estado;
		@NotNull
	    private LocalDateTime fechaRegistro;

}
