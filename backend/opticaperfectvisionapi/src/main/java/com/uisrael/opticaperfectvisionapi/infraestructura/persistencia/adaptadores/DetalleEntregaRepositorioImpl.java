package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleEntregaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleEntregaEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleEntregaJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleEntregaJpaRepositorio;

public class DetalleEntregaRepositorioImpl implements IDetalleEntregaRepositorio {
	private final IDetalleEntregaJpaRepositorio jpaRepositorio;
	private final IDetalleEntregaJpaMapper entityMapper;
	
	
	public DetalleEntregaRepositorioImpl(IDetalleEntregaJpaRepositorio jpaRespositorio,
			IDetalleEntregaJpaMapper entityMapper) {
		super();
		this.jpaRepositorio = jpaRespositorio;
		this.entityMapper = entityMapper;
	}
	@Override
	public DetalleEntrega guardar(DetalleEntrega nuevoDetalleEntrega) {
		DetalleEntregaEntity entity= entityMapper.toEntity(nuevoDetalleEntrega);
		DetalleEntregaEntity guardado= jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);

	}
	@Override
	public Optional<DetalleEntrega> buscarPorId(int idDetalleEntrega) {
		return jpaRepositorio.findById(idDetalleEntrega).map(entityMapper:: toDomain);
	}
	@Override
	public List<DetalleEntrega> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMapper:: toDomain).toList();
	}
	@Override
	public void eliminar(int idDetalleEntrega) {
		jpaRepositorio.deleteById(idDetalleEntrega);		
	}
	
	//1807
	@Override
	public List<DetalleEntrega> findByEstado(Boolean estado) {
	    return jpaRepositorio.findByEstado(estado).stream()
	            .map(entityMapper::toDomain).toList();
	}

	
	   @Override
	    public List<DetalleEntrega> findByCantidad(Integer cantidad) {
	        return jpaRepositorio.findByCantidad(cantidad).stream()
	                .map(entityMapper::toDomain).toList();
	    }

	    @Override
	    public List<DetalleEntrega> buscarPorProductoYEstado(Integer idDetOrden, Boolean estado) {
	        return jpaRepositorio.buscarPorProductoYEstado(idDetOrden, estado).stream()
	                .map(entityMapper::toDomain).toList();
	    }

	    @Override
	    public List<DetalleEntrega> listarTodosOrdenados() {
	        return jpaRepositorio.listarTodosOrdenados().stream()
	                .map(entityMapper::toDomain).toList();
	    }
}
