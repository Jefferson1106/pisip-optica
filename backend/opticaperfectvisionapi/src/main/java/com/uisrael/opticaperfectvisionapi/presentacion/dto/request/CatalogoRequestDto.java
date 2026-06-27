package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CatalogoRequestDto {
	
	@NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
	
	private boolean estado;
}
