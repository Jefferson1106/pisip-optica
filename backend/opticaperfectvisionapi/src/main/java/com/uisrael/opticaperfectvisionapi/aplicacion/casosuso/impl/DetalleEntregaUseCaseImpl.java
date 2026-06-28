package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleEntregaUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleEntregaRepositorio;

public class DetalleEntregaUseCaseImpl implements IDetalleEntregaUseCase {
	private final IDetalleEntregaRepositorio repositorio;

	public DetalleEntregaUseCaseImpl(IDetalleEntregaRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}

	@Override
	public DetalleEntrega guardar(DetalleEntrega nuevoDetalleEntrega) {
		return repositorio.guardar(nuevoDetalleEntrega);
	}

	@Override
	public DetalleEntrega buscarPorId(int idDetalleEntrega) {
		return repositorio.buscarPorId(idDetalleEntrega)
				.orElseThrow(()-> new RuntimeException("Detalle entrega no encontrado"));

	}

	@Override
	public List<DetalleEntrega> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public void eliminar(int idDetalleEntrega) {
		repositorio.eliminar(idDetalleEntrega);
		
	}
	
	

}
