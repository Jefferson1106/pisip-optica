package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleCatalogoRespositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleCatalogoJpaMapper;

import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleCatalogoJpaRepositorio;

public class DetalleCatalogoRepositorioImpl implements IDetalleCatalogoRespositorio{
	private final IDetalleCatalogoJpaRepositorio jpaRepositorio;
	private final IDetalleCatalogoJpaMapper entityMapper;

	
	public DetalleCatalogoRepositorioImpl(IDetalleCatalogoJpaRepositorio jpaRepositorio,
			IDetalleCatalogoJpaMapper entityMapper) {
		super();
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}
	@Override
	public DetalleCatalogo guardar(DetalleCatalogo nuevoDetalleCatalogo) {
		
		DetalleCatalogoEntity entity= entityMapper.toEntity(nuevoDetalleCatalogo);
		DetalleCatalogoEntity guardado= jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);

	}
	@Override
	public Optional<DetalleCatalogo> buscarPorId(int idDetalleCatalogo) {
		return jpaRepositorio.findById(idDetalleCatalogo).map(entityMapper:: toDomain);
	}
	@Override
	public List<DetalleCatalogo> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMapper:: toDomain).toList();
	}
	@Override
	public void eliminar(int idDetalleCatalogo) {
		jpaRepositorio.deleteById(idDetalleCatalogo);
	}
	

}
