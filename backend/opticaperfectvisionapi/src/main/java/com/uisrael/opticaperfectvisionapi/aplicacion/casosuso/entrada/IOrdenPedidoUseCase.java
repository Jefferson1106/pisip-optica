package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;

public interface IOrdenPedidoUseCase {
	
	OrdenPedido guardar(OrdenPedido nuevaOrdenPedido);
	
	OrdenPedido buscarPorId(int idOrdenPedido);
	
	List<OrdenPedido> listarTodos();
	
	OrdenPedido actualizar(int idOrdenPedido, OrdenPedido ordenPedido);
	
}
