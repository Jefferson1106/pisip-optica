package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenEntregaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IOrdenEntregaJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IOrdenEntregaJpaRepositorio;

public class OrdenEntregaRepositorioImpl implements IOrdenEntregaRepositorio {
	
	private final IOrdenEntregaJpaRepositorio jpaRepositorio;
	
	private final IOrdenEntregaJpaMapper entityMapper;

	public OrdenEntregaRepositorioImpl(IOrdenEntregaJpaRepositorio jpaRepositorio,
			IOrdenEntregaJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public OrdenEntrega guardar(OrdenEntrega nuevaOrdenEntrega) {
		OrdenEntregaEntity entity = entityMapper.toEntity(nuevaOrdenEntrega);
		OrdenEntregaEntity guardado=jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<OrdenEntrega> buscarPorId(int idOrdenEntrega) {
		
		return jpaRepositorio.findById(idOrdenEntrega).map(entityMapper :: toDomain);
	}

	@Override
	public List<OrdenEntrega> listarTodos() {
		
		return jpaRepositorio.findAll().stream().map(entityMapper :: toDomain).toList();
	}

	@Override
	public void eliminar(int idOrdenEntrega) {
		// TODO Auto-generated method stub
		
	}
	
	//1807
	@Override
	public List<OrdenEntrega> findByRecibido(Boolean recibido) {
	    return jpaRepositorio.findByRecibido(recibido).stream()
	            .map(entityMapper::toDomain).toList();
	}

	@Override
	public List<OrdenEntrega> findByFechaEntrega(LocalDate fechaEntrega) {
	    return jpaRepositorio.findByFechaEntrega(fechaEntrega).stream()
	            .map(entityMapper::toDomain).toList();
	}

	@Override
	public List<OrdenEntrega> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
	    return jpaRepositorio.buscarPorRangoFechas(inicio, fin).stream()
	            .map(entityMapper::toDomain).toList();
	}

	@Override
	public List<OrdenEntrega> buscarPorObservaciones(String texto) {
	    return jpaRepositorio.buscarPorObservaciones(texto).stream()
	            .map(entityMapper::toDomain).toList();
	}

	@Override
	public List<OrdenEntrega> listarTodosOrdenados() {
	    return jpaRepositorio.listarTodosOrdenados().stream()
	            .map(entityMapper::toDomain).toList();
	}



}
