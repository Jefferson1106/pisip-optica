package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;

public interface IDetalleEntregaRepositorio {
	DetalleEntrega guardar(DetalleEntrega nuevoDetalleEntrega);
	Optional<DetalleEntrega> buscarPorId(int idDetalleEntrega);
	List<DetalleEntrega> listarTodos();
	void eliminar (int idDetalleEntrega);

}
