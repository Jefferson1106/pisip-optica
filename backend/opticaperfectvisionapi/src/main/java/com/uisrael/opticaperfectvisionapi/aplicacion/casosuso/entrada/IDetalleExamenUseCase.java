package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;

public interface IDetalleExamenUseCase {
	
	DetalleExamen guardar(DetalleExamen nuevoDetalleExamn);
	DetalleExamen buscarPorId(int idDetalleExamen);
	List<DetalleExamen> listarTodos();
	DetalleExamen actualizar(int idDetalleExamen, DetalleExamen detalleExamen);
	boolean existeDescripcion(String descripcionExamen);
	
	boolean existeNombreParaOtroDetalle(String nombreDetalle, int idDetalleExamen);

}
