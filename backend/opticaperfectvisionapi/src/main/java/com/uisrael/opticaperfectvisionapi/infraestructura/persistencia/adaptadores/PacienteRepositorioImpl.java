package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IPacienteRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IPacienteJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IPacienteJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IUsuarioAdministradorJpaRepositorio;

public class PacienteRepositorioImpl implements IPacienteRepositorio{
	
	private final IPacienteJpaRepositorio jpaRepositorio;
	private final IPacienteJpaMapper entityMapper;
	private final IUsuarioAdministradorJpaRepositorio usuarioAdministradorRepositorio;

	public PacienteRepositorioImpl(IPacienteJpaRepositorio jpaRepositorio, IPacienteJpaMapper entityMapper,
			IUsuarioAdministradorJpaRepositorio usuarioAdministradorRepositorio) {
		
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
		this.usuarioAdministradorRepositorio = usuarioAdministradorRepositorio;
	}
	@Override
	public Paciente guardar(Paciente nuevoPaciente) {
		PacienteEntity entity=entityMapper.toEntity(nuevoPaciente);
		entity.setUsuarioAdministrador(resolverUsuarioAdministrador(entity.getUsuarioAdministrador()));
		PacienteEntity guardado=jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}
	@Override
	public Optional<Paciente> buscarPorId(int idPaciente) {
		
		return jpaRepositorio.findById(idPaciente).map(entityMapper::toDomain);
	}
	@Override
	public List<Paciente> listarTodos() {
		
		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}
	@Override
	public void eliminar(String cedula) {
		PacienteEntity paciente = jpaRepositorio.findByCedula(cedula)
				.orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
		jpaRepositorio.deleteById(paciente.getIdPaciente());
	}

	private UsuarioAdministradorEntity resolverUsuarioAdministrador(UsuarioAdministradorEntity usuarioAdministrador) {
		if (usuarioAdministrador == null || usuarioAdministrador.getIdUsuario() == null) {
			return null;
		}

		return usuarioAdministradorRepositorio.findById(usuarioAdministrador.getIdUsuario())
				.orElseThrow(() -> new RuntimeException("Usuario administrador no encontrado"));
	}

	
	
	
}
