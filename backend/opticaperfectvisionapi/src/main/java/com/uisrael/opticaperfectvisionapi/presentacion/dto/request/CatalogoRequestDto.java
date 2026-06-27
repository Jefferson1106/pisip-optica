package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogoRequestDto {
    
	@NotBlank
    private String descripcion;
	@NotNull
	private Boolean estado;
}
