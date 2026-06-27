package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenPedidoRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IOrdenPedidoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IOrdenPedidoJpaRepositorio;

public class OrdenPedidoRepositorioImpl implements IOrdenPedidoRepositorio {
	
	private final IOrdenPedidoJpaRepositorio jpaRepositorio;
	private final IOrdenPedidoJpaMapper entityMapper;
	public OrdenPedidoRepositorioImpl(IOrdenPedidoJpaRepositorio jpaRepositorio, IOrdenPedidoJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}
	@Override
	public OrdenPedido guardar(OrdenPedido nuevaOrdenPedido) {
		OrdenPedidoEntity entity=entityMapper.toEntity(nuevaOrdenPedido);
		OrdenPedidoEntity guardado=jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}
	@Override
	public Optional<OrdenPedido> buscarPorId(int idOrdenPedido) {
		
		return jpaRepositorio.findById(idOrdenPedido).map(entityMapper :: toDomain);
	}
	@Override
	public List<OrdenPedido> listarTodos() {
		
		return jpaRepositorio.findAll().stream().map(entityMapper :: toDomain).toList();
	}

	
	

}
