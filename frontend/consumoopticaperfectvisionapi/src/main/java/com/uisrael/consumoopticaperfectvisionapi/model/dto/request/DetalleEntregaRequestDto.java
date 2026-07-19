package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DetalleEntregaRequestDto {

    private Integer cantidad;

    private Boolean estado;

    private LocalDateTime fechaRegistro;

    private Integer idEntrega;

    private Integer idProducto;
}

