package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DetalleOrdenRequestDto {

    @NotNull
    private Integer idPedido;

    @NotNull
    private Integer idProducto;

    private Integer idMaterial;

    private Integer idMarco;

    private Integer idTipoLente;

    private String tratamiento;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    private BigDecimal precioUnitario;

    @NotNull
    private LocalDateTime fechaRegistro;

}
