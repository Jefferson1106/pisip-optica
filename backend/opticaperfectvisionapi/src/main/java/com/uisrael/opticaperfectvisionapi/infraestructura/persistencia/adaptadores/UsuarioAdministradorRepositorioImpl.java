package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IUsuarioAdministradorRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IUsuarioAdministradorJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IUsuarioAdministradorJpaRepositorio;

public class UsuarioAdministradorRepositorioImpl implements IUsuarioAdministradorRepositorio{
	
	private final IUsuarioAdministradorJpaRepositorio jpaRepositorio;
	
	private final IUsuarioAdministradorJpaMapper entityMapper;

	public UsuarioAdministradorRepositorioImpl(IUsuarioAdministradorJpaRepositorio jpaRepositorio,
			IUsuarioAdministradorJpaMapper entityMapper) {
		
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public UsuarioAdministrador guardar(UsuarioAdministrador nuevoUsuarioAdministrador) {
		UsuarioAdministradorEntity entity=entityMapper.toEntity(nuevoUsuarioAdministrador);
		UsuarioAdministradorEntity guardado=jpaRepositorio.save(entity);
		
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<UsuarioAdministrador> buscarPorId(int idUsuarioAdministrador) {
		
		return jpaRepositorio.findById(idUsuarioAdministrador).map(entityMapper::toDomain);
	}

	@Override
	public List<UsuarioAdministrador> listarTodos() {
		
		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}


	
	
}
