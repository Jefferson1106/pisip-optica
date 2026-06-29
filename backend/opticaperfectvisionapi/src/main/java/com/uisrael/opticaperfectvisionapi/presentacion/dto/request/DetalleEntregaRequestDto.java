package com.uisrael.opticaperfectvisionapi.presentacion.dto.request;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetalleEntregaRequestDto {

    @NotNull
    private Integer cantidad;

    @NotNull
    private Boolean estado;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @NotNull
    private Integer idEntrega;

    @NotNull
    private Integer idProducto;
}

