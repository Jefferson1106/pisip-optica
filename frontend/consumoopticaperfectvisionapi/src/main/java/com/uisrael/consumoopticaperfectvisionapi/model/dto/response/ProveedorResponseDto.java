package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import lombok.Data;

@Data
public class ProveedorResponseDto {
	private Integer idProveedor;
	private String nombre;
	private String identificacion;
	private String correo;
	private String telefono;
	private String direccion;
	private boolean estado;
}
