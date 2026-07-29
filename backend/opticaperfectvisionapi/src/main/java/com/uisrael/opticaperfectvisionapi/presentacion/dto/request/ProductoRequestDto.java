package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoRequestDto {
	@NotBlank(message = "El código es obligatorio")
	private String codigo;
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	private String descripcion;
	@NotNull(message = "El precio es obligatorio")
	@DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
	private BigDecimal precio;
	@NotNull(message = "El proveedor es obligatorio")
	private Integer idProveedor;
	private boolean estado = true;
}
