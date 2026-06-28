package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrdenEntregaRequestDto {
	
	@NotBlank
    private OrdenPedidoEntity ordenPedido;
    @NotBlank
    private LocalDate fechaEntrega;
    @NotBlank
    private Boolean recibido;
    @NotBlank
    private String observaciones;
    @NotBlank
    private LocalDateTime fechaRegistro;
}
