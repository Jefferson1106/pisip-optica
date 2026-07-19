package com.uisrael.consumoopticaperfectvisionapi.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
@Data
public class OrdenPedidoRequestDto {

	    private Integer idExamen;

	    private Integer idPaciente;

	    private LocalDate fechaPedido;

	    private LocalDate fechaEntrega;

	    private Integer idEstadoPedido;

	    private LocalDateTime fechaRegistro;

}
