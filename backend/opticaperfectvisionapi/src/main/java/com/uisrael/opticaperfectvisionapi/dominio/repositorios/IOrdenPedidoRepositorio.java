package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;

public interface IOrdenPedidoRepositorio {
	
	OrdenPedido guardar(OrdenPedido nuevaOrdenPedido);
	
	Optional<OrdenPedido> buscarPorId(int idOrdenPedido);
	
	List<OrdenPedido> listarTodos();
	
	OrdenPedido actualizar(int idOrdenPedido, OrdenPedido actualizado);

	void eliminar(int idOrdenPedido);
	

}
