package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetalleOrdenRequestDto {

    @NotNull
    private Integer idPedido;

    @NotNull
    private Integer idMaterial;

    @NotNull
    private Integer idMarco;

    @NotNull
    private Integer idTipoLente;

    @NotNull
    private String tratamiento;

    @NotNull
    private Integer cantidad;

    @NotNull
    private BigDecimal precioUnitario;

    @NotNull
    private LocalDateTime fechaRegistro;

}
