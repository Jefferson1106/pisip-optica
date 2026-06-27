package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;

public interface IDetalleOrdenRepositorio {
	DetalleOrden guardar(DetalleOrden nuevoDetalleOrden);
	Optional<DetalleOrden> buscarPorId(int idDetalleOrden);
	List<DetalleOrden> listarTodos();
	void eliminar (int idDetalleOrden);

}
