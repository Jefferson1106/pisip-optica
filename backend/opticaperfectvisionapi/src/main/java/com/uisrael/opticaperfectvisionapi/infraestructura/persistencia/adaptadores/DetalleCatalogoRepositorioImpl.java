package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleCatalogoRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleCatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleCatalogoJpaRepositorio;

public class DetalleCatalogoRepositorioImpl implements IDetalleCatalogoRepositorio {

	private final IDetalleCatalogoJpaRepositorio jpaRepositorio;
	private final IDetalleCatalogoJpaMapper entityMapper;

	public DetalleCatalogoRepositorioImpl(IDetalleCatalogoJpaRepositorio jpaRepositorio,
			IDetalleCatalogoJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public DetalleCatalogo guardar(DetalleCatalogo nuevoDetalleCatalogo) {
		DetalleCatalogoEntity entity = entityMapper.toEntity(nuevoDetalleCatalogo);
		DetalleCatalogoEntity guardado = jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<DetalleCatalogo> buscarPorId(int idDetalleCatalogo) {
		return jpaRepositorio.findByIdWithCatalogo(idDetalleCatalogo).map(entityMapper::toDomain);
	}

	@Override
	public List<DetalleCatalogo> listarTodos() {
		return jpaRepositorio.findAllWithCatalogo().stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public DetalleCatalogo actualizar(int id, DetalleCatalogo detalleCatalogo) {
		DetalleCatalogoEntity existente = jpaRepositorio.findByIdWithCatalogo(id)
				.orElseThrow(() -> new RuntimeException("Detalle de catálogo no encontrado"));

		existente.setCatalogo(detalleCatalogo.getCatalogo());
		existente.setNombre(detalleCatalogo.getNombre());
		existente.setIdentificador(detalleCatalogo.getIdentificador());
		existente.setEstado(detalleCatalogo.isEstado());

		DetalleCatalogoEntity guardado = jpaRepositorio.save(existente);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public DetalleCatalogo actualizarEstado(int id, DetalleCatalogo detalleCatalogo) {
		DetalleCatalogoEntity existente = jpaRepositorio.findByIdWithCatalogo(id)
				.orElseThrow(() -> new RuntimeException("Detalle de catálogo no encontrado"));

		existente.setEstado(detalleCatalogo.isEstado());

		DetalleCatalogoEntity guardado = jpaRepositorio.save(existente);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public boolean existeNombre(String nombre) {
		return jpaRepositorio.existsByNombreIgnoreCase(nombre);
	}

	@Override
	public boolean existeNombreParaOtro(String nombre, int idDetalleCatalogo) {
		return jpaRepositorio.existsByNombreIgnoreCaseAndIdDetalleCatalogoNot(nombre, idDetalleCatalogo);
	}
}
