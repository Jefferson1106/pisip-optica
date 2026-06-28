package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.ICatalogoRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.CatalogoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.ICatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.ICatalogoJpaRepositorio;

public class CatalogoRepositorioImpl implements ICatalogoRepositorio {

	private final ICatalogoJpaRepositorio jpaRepositorio;
	private final ICatalogoJpaMapper entityMaper;
	
	public CatalogoRepositorioImpl(ICatalogoJpaRepositorio jpaRepositorio, ICatalogoJpaMapper entityMaper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMaper = entityMaper;
	}

	@Override
	public Catalogo guardar(Catalogo nuevoCatalogo) {
		CatalogoEntity entity = entityMaper.toEntity(nuevoCatalogo);
		CatalogoEntity guardado = jpaRepositorio.save(entity);
		return entityMaper.toDomain(guardado);
	}

	@Override
	public Optional<Catalogo> buscarPorId(int idCatalogo) {
		return jpaRepositorio.findById(idCatalogo).map(entityMaper::toDomain);
	}

	@Override
	public List<Catalogo> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMaper::toDomain).toList();
	}

	@Override
	public Catalogo actualizar(int id, Catalogo catalogo) {
		CatalogoEntity existente = jpaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Catalogo no encontrado"));

		existente.setDescripcion(catalogo.getDescripcion());
		existente.setEstado(catalogo.isEstado());

		CatalogoEntity guardado = jpaRepositorio.save(existente);
		return entityMaper.toDomain(guardado);
	}

	@Override
	public Catalogo actualizarEstado(int id, Catalogo catalogo) {
		CatalogoEntity existente = jpaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Catalogo no encontrado"));

		existente.setEstado(catalogo.isEstado());

		CatalogoEntity guardado = jpaRepositorio.save(existente);
		return entityMaper.toDomain(guardado);
	}
	
	@Override
	public boolean existeDescripcion(String descripcion) {
		return jpaRepositorio.existsByDescripcionIgnoreCase(descripcion);
	}

	@Override
	public boolean existeNombreParaOtro(String descripcion, int idCatalogo) {
		return jpaRepositorio.existsByDescripcionIgnoreCaseAndIdCatalogoNot(descripcion, idCatalogo);
	}



}
