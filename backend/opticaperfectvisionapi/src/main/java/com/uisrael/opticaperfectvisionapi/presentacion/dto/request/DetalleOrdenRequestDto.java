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

    private OrdenPedidoEntity ordenPedido;
	
    private DetalleCatalogoEntity material;
	
    private DetalleCatalogoEntity marco;
	
    private DetalleCatalogoEntity tipoLente;
	
    private String tratamiento;
	
    private Integer cantidad;
	
    private BigDecimal precioUnitario;
	
    private LocalDateTime fechaRegistro;

}
