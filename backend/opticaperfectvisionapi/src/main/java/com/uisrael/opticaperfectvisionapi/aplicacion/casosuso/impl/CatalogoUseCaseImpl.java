package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.ICatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.ICatalogoRepositorio;

public class CatalogoUseCaseImpl implements ICatalogoUseCase {

	private final ICatalogoRepositorio repositorio;

	public CatalogoUseCaseImpl(ICatalogoRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public Catalogo guardar(Catalogo nuevoCatalogo) {
		if (repositorio.existeDescripcion(nuevoCatalogo.getDescripcion().trim())) {
			throw new RuntimeException("Ya existe un catalogo con esa descripcion");
		}
		return repositorio.guardar(nuevoCatalogo);
	}

	@Override
	public Catalogo buscarPorId(int idCatalogo) {
		return  repositorio.buscarPorId(idCatalogo)
				.orElseThrow(() -> new RuntimeException("Catalogo no encontrado"));
	}

	@Override
	public List<Catalogo> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public Catalogo actualizar(int id, Catalogo catalogo) {
		repositorio.buscarPorId(id).orElseThrow(() -> new RuntimeException("Cargo no encontrado"));

		if (repositorio.existeNombreParaOtro(catalogo.getDescripcion().trim(), id)) {
			throw new RuntimeException("Ya existe otro cargo con ese nombre");
		}

		Catalogo actualizado = new Catalogo(id, catalogo.getDescripcion(), catalogo.isEstado());

		return repositorio.actualizar(id, actualizado);
	}
	
	@Override
	public Catalogo actualizarEstado(int id, boolean estado) {
		Catalogo actual = repositorio.buscarPorId(id).orElseThrow(() -> new RuntimeException("Cargo no encontrado"));

		// Solo cambia el estado
		Catalogo actualizado = new Catalogo(actual.getIdCatalogo(), // o id, según tu constructor
				actual.getDescripcion(), estado);

		return repositorio.actualizarEstado(id, actualizado);
	}
	
	@Override
	public boolean existeDescripcion(String descripcion) {
		return repositorio.existeDescripcion(descripcion.trim());
	}






}
