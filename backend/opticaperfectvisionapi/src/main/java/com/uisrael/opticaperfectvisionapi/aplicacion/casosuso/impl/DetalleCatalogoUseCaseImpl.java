package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleCatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleCatalogoRepositorio;

public class DetalleCatalogoUseCaseImpl implements IDetalleCatalogoUseCase {

	private final IDetalleCatalogoRepositorio repositorio;

	public DetalleCatalogoUseCaseImpl(IDetalleCatalogoRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public DetalleCatalogo guardar(DetalleCatalogo nuevoDetalleCatalogo) {
		if (repositorio.existeNombre(nuevoDetalleCatalogo.getNombre().trim())) {
			throw new RuntimeException("Ya existe un detalle de catálogo con ese nombre");
		}
		return repositorio.guardar(nuevoDetalleCatalogo);
	}

	@Override
	public DetalleCatalogo buscarPorId(int idDetalleCatalogo) {
		return repositorio.buscarPorId(idDetalleCatalogo)
				.orElseThrow(() -> new RuntimeException("Detalle de catálogo no encontrado"));
	}

	@Override
	public List<DetalleCatalogo> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public DetalleCatalogo actualizar(int id, DetalleCatalogo detalleCatalogo) {
		repositorio.buscarPorId(id).orElseThrow(() -> new RuntimeException("Detalle de catálogo no encontrado"));

		if (repositorio.existeNombreParaOtro(detalleCatalogo.getNombre().trim(), id)) {
			throw new RuntimeException("Ya existe otro detalle de catálogo con ese nombre");
		}

		DetalleCatalogo actualizado = new DetalleCatalogo(
				id,
				detalleCatalogo.getCatalogo(),
				detalleCatalogo.getNombre(),
				detalleCatalogo.getIdentificador(),
				detalleCatalogo.isEstado());

		return repositorio.actualizar(id, actualizado);
	}

	@Override
	public DetalleCatalogo actualizarEstado(int id, boolean estado) {
		DetalleCatalogo actual = repositorio.buscarPorId(id)
				.orElseThrow(() -> new RuntimeException("Detalle de catálogo no encontrado"));

		DetalleCatalogo actualizado = new DetalleCatalogo(
				actual.getIdDetalleCatalogo(),
				actual.getCatalogo(),
				actual.getNombre(),
				actual.getIdentificador(),
				estado);

		return repositorio.actualizarEstado(id, actualizado);
	}

	@Override
	public boolean existeNombre(String nombre) {
		return repositorio.existeNombre(nombre.trim());
	}
}
