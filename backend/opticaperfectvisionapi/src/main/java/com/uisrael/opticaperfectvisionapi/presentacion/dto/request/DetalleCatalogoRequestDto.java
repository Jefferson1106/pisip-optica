package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetalleCatalogoRequestDto {

	@NotNull
	private Integer idCatalogo;

	@NotBlank
	private String nombre;

	@NotBlank
	private String identificador;

	@NotNull
	private Boolean estado;
}
