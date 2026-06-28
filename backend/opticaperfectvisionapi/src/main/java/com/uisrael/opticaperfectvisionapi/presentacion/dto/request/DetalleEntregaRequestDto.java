package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class DetalleEntregaRequestDto {
	
	@NotBlank
    private OrdenEntregaEntity ordenEntrega;
	@NotBlank
    private DetalleOrdenEntity producto;
	@NotBlank
    private Integer cantidad;
	@NotBlank
    private Boolean estado;
	@NotBlank
    private LocalDateTime fechaRegistro;

}
