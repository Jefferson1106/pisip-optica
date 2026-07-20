package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DetalleOrdenRequestDto {

    private Integer idPedido;

    private Integer idMaterial;

    private Integer idMarco;

    private Integer idTipoLente;

    private String tratamiento;

    private Integer cantidad;

    private BigDecimal precioUnitario;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegistro;

}
