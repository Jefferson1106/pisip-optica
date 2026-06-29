package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleOrdenRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleOrdenJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleOrdenJpaRepositorio;

public class DetalleOrdenRepositorioImpl implements IDetalleOrdenRepositorio {
	
	private final IDetalleOrdenJpaRepositorio jpaRepositorio;
	private final IDetalleOrdenJpaMapper entityMapper;

	
	public DetalleOrdenRepositorioImpl(IDetalleOrdenJpaRepositorio jpaRepositorio,
			IDetalleOrdenJpaMapper entityMapper) {
		super();
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}
	public DetalleOrden guardar(DetalleOrden nuevoDetalleOrden) {
		DetalleOrdenEntity entity= entityMapper.toEntity(nuevoDetalleOrden);
		DetalleOrdenEntity guardado= jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);

	}
	@Override
	public Optional<DetalleOrden> buscarPorId(int idDetalleOrden) {
		return jpaRepositorio.findById(idDetalleOrden).map(entityMapper:: toDomain);
	}
	@Override
	public List<DetalleOrden> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMapper:: toDomain).toList();
	}
	@Override
	public void eliminar(int idDetalleOrden) {
		jpaRepositorio.deleteById(idDetalleOrden);		
	}
	@Override
	public DetalleOrden actualizar(int idDetalleOrden, DetalleOrden detalleOrden) {
		DetalleOrdenEntity existente = jpaRepositorio.findById(idDetalleOrden).orElseThrow(() -> new RuntimeException("Detalle orden no encontrado"));
		
		existente.setIdDetOrden(detalleOrden.getIdDetOrden());
		existente.setOrdenPedido(detalleOrden.getOrdenPedido());
		existente.setMaterial(detalleOrden.getMaterial());
		existente.setMarco(detalleOrden.getMarco());
		existente.setTipoLente(detalleOrden.getTipoLente());
		existente.setTratamiento(detalleOrden.getTratamiento());
		existente.setCantidad(detalleOrden.getCantidad());
		existente.setPrecioUnitario(detalleOrden.getPrecioUnitario());
		existente.setFechaRegistro(detalleOrden.getFechaRegistro());
		
		DetalleOrdenEntity guardado = jpaRepositorio.save(existente);
		
		return entityMapper.toDomain(guardado);
	}
	
	

}
