package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DetalleCatalogoRequestDto {

	private Integer idDetalleCatalogo;

	@NotNull(message = "Debe seleccionar un catalogo")
	private Integer idCatalogo;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 120, message = "El nombre no debe exceder 120 caracteres")
	private String nombre;

	@NotBlank(message = "El identificador es obligatorio")
	@Pattern(regexp = "^[A-Za-z0-9_-]{2,20}$", message = "Identificador invalido")
	private String identificador;

	@NotNull(message = "Debe indicar si el detalle esta activo")
	private Boolean estado;
}
