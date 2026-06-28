package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;

public interface IOrdenEntregaRepositorio {
	
	OrdenEntrega guardar(OrdenEntrega nuevaOrdenEntrega);
	
	Optional<OrdenEntrega> buscarPorId(int idOrdenEntrega);
	
	List<OrdenEntrega> listarTodos();
	
	void eliminar (int idOrdenEntrega);
}
