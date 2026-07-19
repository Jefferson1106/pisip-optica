package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.time.LocalDate;
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
	        .orElseThrow(() -> new RuntimeException("Orden de entrega no encontrada"));
	}

	@Override
	public List<OrdenEntrega> listarTodos() {
	    return repositorio.listarTodos();
	}

	@Override
	public OrdenEntrega actualizar(int idOrdenEntrega, OrdenEntrega ordenEntregaActualizada) {
	    OrdenEntrega existente = buscarPorId(idOrdenEntrega);
	    existente.setOrdenPedido(ordenEntregaActualizada.getOrdenPedido());
	    existente.setFechaEntrega(ordenEntregaActualizada.getFechaEntrega());
	    existente.setFechaRegistro(ordenEntregaActualizada.getFechaRegistro());
	    existente.setObservaciones(ordenEntregaActualizada.getObservaciones());
	    existente.setRecibido(ordenEntregaActualizada.getRecibido());
	    return repositorio.guardar(existente);
	}

	@Override
	public OrdenEntrega actualizarRecibido(int idOrdenEntrega, Boolean recibido) {
	    OrdenEntrega existente = buscarPorId(idOrdenEntrega);
	    existente.setRecibido(recibido);
	    return repositorio.guardar(existente);
	}



	    // 1807
	    @Override
	    public List<OrdenEntrega> findByRecibido(Boolean recibido) {
	        return repositorio.findByRecibido(recibido);
	    }

	    @Override
	    public List<OrdenEntrega> findByFechaEntrega(LocalDate fechaEntrega) {
	        return repositorio.findByFechaEntrega(fechaEntrega);
	    }

	    @Override
	    public List<OrdenEntrega> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
	        return repositorio.buscarPorRangoFechas(inicio, fin);
	    }

	    @Override
	    public List<OrdenEntrega> buscarPorObservaciones(String texto) {
	        return repositorio.buscarPorObservaciones(texto);
	    }

	    @Override
	    public List<OrdenEntrega> listarTodosOrdenados() {
	        return repositorio.listarTodosOrdenados();
	    }
	

}
