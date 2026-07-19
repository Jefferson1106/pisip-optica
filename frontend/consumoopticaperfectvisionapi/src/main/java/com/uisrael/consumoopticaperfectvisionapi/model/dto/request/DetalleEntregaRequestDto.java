package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DetalleEntregaRequestDto {

    private Integer cantidad;
    private Boolean estado;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegistro;
    private Integer idEntrega;
    private Integer idProducto;
}

