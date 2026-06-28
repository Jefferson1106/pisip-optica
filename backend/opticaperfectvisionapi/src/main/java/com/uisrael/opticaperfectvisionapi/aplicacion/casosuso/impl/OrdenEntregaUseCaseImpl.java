package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IOrdenEntregaUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenEntregaRepositorio;

public class OrdenEntregaUseCaseImpl implements IOrdenEntregaUseCase {
	private final IOrdenEntregaRepositorio repositorio;

	public OrdenEntregaUseCaseImpl(IOrdenEntregaRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}

	@Override
	public OrdenEntrega guardar(OrdenEntrega nuevaOrdenEntrega) {
		return repositorio.guardar(nuevaOrdenEntrega);
	}

	@Override
	public OrdenEntrega buscarPorId(int idOrdenEntrega) {
		return repositorio.buscarPorId(idOrdenEntrega)
				.orElseThrow(()-> new RuntimeException("Orden de entrega no encontrado"));

	}

	@Override
	public List<OrdenEntrega> listarTodos() {
		return repositorio.listarTodos();
	}
	

}
