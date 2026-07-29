package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CatalogoRequestDto {

	private Integer idCatalogo;
    
	@NotBlank(message = "La descripcion es obligatoria")
	@Size(max = 120, message = "La descripcion no debe exceder 120 caracteres")
    private String descripcion;

	@NotNull(message = "Debe indicar si el catalogo esta activo")
	private Boolean estado;
}
