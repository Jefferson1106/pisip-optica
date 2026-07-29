package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductoResponseDto {
	private Integer idProducto;
	private String codigo;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private Integer idProveedor;
	private String proveedorNombre;
	private boolean estado;
}
