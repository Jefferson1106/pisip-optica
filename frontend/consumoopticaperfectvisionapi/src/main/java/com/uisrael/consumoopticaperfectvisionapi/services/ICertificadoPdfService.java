package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;
import java.util.Map;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;

public interface ICertificadoPdfService {

	byte[] generarCertificadoPdf(ExamenVisualResponseDto certificado, DetalleExamenResponseDto detalleExamen);

	byte[] generarOrdenPedidoPdf(OrdenPedidoResponseDto pedido, PacienteResponseDto paciente,
			List<DetalleOrdenResponseDto> detalles, Map<Integer, String> catalogosPorId);
}
