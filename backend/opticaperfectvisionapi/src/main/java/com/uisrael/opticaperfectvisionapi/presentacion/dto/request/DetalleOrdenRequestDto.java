package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DetalleOrdenRequestDto {
	
	@NotBlank
	private Integer idDetOrden;
	@NotNull
    private OrdenPedidoEntity ordenPedido;
	@NotNull
    private DetalleCatalogoEntity material;
	@NotNull
    private DetalleCatalogoEntity marco;
	@NotNull
    private DetalleCatalogoEntity tipoLente;
	@NotNull
    private String tratamiento;
	@NotNull
    private Integer cantidad;
	@NotNull
    private BigDecimal precioUnitario;
	@NotNull
    private LocalDateTime fechaRegistro;

}
