package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private LocalDateTime fechaRegistro;

}
