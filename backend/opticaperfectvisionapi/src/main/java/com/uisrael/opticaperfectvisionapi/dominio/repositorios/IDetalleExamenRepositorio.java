package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;

public interface IDetalleExamenRepositorio {
	DetalleExamen guardar(DetalleExamen nuevoDetalleExamn);
	Optional<DetalleExamen> buscarPorId(int idDetalleExamen);
	List<DetalleExamen> listarTodos();
	void eliminar (int idDetalleExamen);
	
	boolean existeDescripcion(String descripcionExamen);
	
	DetalleExamen actualizar(int idDetalleExamen, DetalleExamen actualizado);
}
