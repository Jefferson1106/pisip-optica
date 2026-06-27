package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IExamenVisualRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IExamenVisualJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IExamenVisualJpaRepositorio;

public class ExamenVisualRespositorioImpl implements IExamenVisualRepositorio {
	
	private final IExamenVisualJpaRepositorio jpaRepositorio;
	private final IExamenVisualJpaMapper entityMapper;
	
	
	
	public ExamenVisualRespositorioImpl(IExamenVisualJpaRepositorio jpaRepositorio,
			IExamenVisualJpaMapper entityMapper) {
		super();
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}
	@Override
	public ExamenVisual guardar(ExamenVisual nuevoExamenVisual) {
		ExamenVisualEntity entity= entityMapper.toEntity(nuevoExamenVisual);
		ExamenVisualEntity guardado= jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);

	}
	@Override
	public Optional<ExamenVisual> buscarPorId(int idExamenVisual) {
		
		return jpaRepositorio.findById(idExamenVisual).map(entityMapper:: toDomain);
		}

	@Override
	public List<ExamenVisual> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void eliminar(int idExamenVisual) {
		jpaRepositorio.deleteById(idExamenVisual);
	}
	

	
	

}
