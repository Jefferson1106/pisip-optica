package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;
import java.time.LocalDateTime;

import lombok.Data;

@Data

public class DetalleEntregaResponseDto {
	private Integer idDetEntrega;
    private Integer cantidad;
    private Boolean estado;
    private LocalDateTime fechaRegistro;
    private Integer idEntrega;
	private Integer idProducto;
    
  


}
