package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleExamenResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IDetalleExamenDtoMapper;

import jakarta.validation.Valid;

public interface IDetalleExamenUseCase {
	
	DetalleExamen guardar(DetalleExamen nuevoDetalleExamn);
	DetalleExamen buscarPorId(int idDetalleExamen);
	List<DetalleExamen> listarTodos();
	
	DetalleExamen actualizar(int idDetalleExamen, DetalleExamen detalleExamen);
	
	boolean existeDescripcion(String descripcionExamen);
	
	boolean existeNombreParaOtroDetalle(String nombreDetalle, int idDetalleExamen);
	


}
