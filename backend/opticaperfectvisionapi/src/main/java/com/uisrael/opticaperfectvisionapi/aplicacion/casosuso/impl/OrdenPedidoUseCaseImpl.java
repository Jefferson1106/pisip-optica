package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IOrdenPedidoUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenPedidoRepositorio;

public class OrdenPedidoUseCaseImpl implements IOrdenPedidoUseCase{
	
	private final IOrdenPedidoRepositorio repositorio;

	public OrdenPedidoUseCaseImpl(IOrdenPedidoRepositorio repositorio) {

		this.repositorio = repositorio;
	}
	
	@Override
	public OrdenPedido guardar(OrdenPedido nuevaOrdenPedido) {
		
		return repositorio.guardar(nuevaOrdenPedido);
		
	}
	
	@Override
	public OrdenPedido buscarPorId(int idOrdenPedido) {
		return repositorio.buscarPorId(idOrdenPedido)
				.orElseThrow(()-> new RuntimeException("Pedido no encontrado"));
		
	}
	
	@Override
	public List<OrdenPedido> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public OrdenPedido actualizar(int idOrdenPedido, OrdenPedido ordenPedido) {
		
		repositorio.buscarPorId(idOrdenPedido).orElseThrow(()-> new RuntimeException("Orden Pedido no encontrado"));
		
		OrdenPedido actualizado = new OrdenPedido(ordenPedido.getIdPedido(),ordenPedido.getExamenVisual(),ordenPedido.getPaciente(),
				ordenPedido.getFechaPedido(),ordenPedido.getFechaEntrega(),ordenPedido.getEstado(),
				ordenPedido.getFechaRegistro());
		
		return repositorio.actualizar(idOrdenPedido, actualizado);
	}
	
	

}
