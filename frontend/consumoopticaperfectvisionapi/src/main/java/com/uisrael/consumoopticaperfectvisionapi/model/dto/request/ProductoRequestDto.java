package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoRequestDto {
	private Integer idProducto;
	@NotBlank(message = "El código es obligatorio")
	private String codigo;
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	private String descripcion;
	@NotNull(message = "El precio es obligatorio")
	@DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
	private BigDecimal precio;
	@NotNull(message = "El proveedor es obligatorio")
	private Integer idProveedor;
	private boolean estado = true;
}
