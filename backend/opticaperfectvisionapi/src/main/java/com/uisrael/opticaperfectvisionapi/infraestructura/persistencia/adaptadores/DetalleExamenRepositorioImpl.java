package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleExamenRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleExamenEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleExamenJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleExamenJpaRepositorio;

public class DetalleExamenRepositorioImpl implements IDetalleExamenRepositorio {
	private final IDetalleExamenJpaRepositorio jpaRepositorio;
	private final IDetalleExamenJpaMapper entityMapper;
	
	
	public DetalleExamenRepositorioImpl(IDetalleExamenJpaRepositorio jpaRepositorio,
			IDetalleExamenJpaMapper entityMapper) {
		super();
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}
	@Override
	public DetalleExamen guardar(DetalleExamen nuevoDetalleExamn) {
		DetalleExamenEntity entity= entityMapper.toEntity(nuevoDetalleExamn);
		DetalleExamenEntity guardado= jpaRepositorio.save(entity);
	    return entityMapper.toDomain(guardado);

	}
	@Override
	public Optional<DetalleExamen> buscarPorId(int idDetalleExamen) {
		return jpaRepositorio.findById(idDetalleExamen).map(entityMapper:: toDomain);
	}
	@Override
	public List<DetalleExamen> listarTodos() {
	
	return jpaRepositorio.findAll().stream().map(entityMapper:: toDomain).toList();
	}

	@Override
	public void eliminar(int idDetalleExamen) {
		jpaRepositorio.deleteById(idDetalleExamen);
		
	}
	
	

}
