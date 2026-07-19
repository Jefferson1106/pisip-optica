package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;
<<<<<<< HEAD
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
=======

import java.time.LocalDateTime;
>>>>>>> 436a4109019697430676384dfd08e323bbee58a2
import lombok.Data;

@Data
public class DetalleEntregaRequestDto {

    private Integer cantidad;

    private Boolean estado;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegistro;
    private Integer idEntrega;


    private Boolean estado;

    private LocalDateTime fechaRegistro;

    private Integer idEntrega;


    private Integer idProducto;
}

