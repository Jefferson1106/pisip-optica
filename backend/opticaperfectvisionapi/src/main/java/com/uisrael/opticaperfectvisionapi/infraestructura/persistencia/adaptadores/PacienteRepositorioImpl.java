package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IPacienteRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IPacienteJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IPacienteJpaRepositorio;

public class PacienteRepositorioImpl implements IPacienteRepositorio{
	
	private final IPacienteJpaRepositorio jpaRepositorio;
	private final IPacienteJpaMapper entityMapper;
	public PacienteRepositorioImpl(IPacienteJpaRepositorio jpaRepositorio, IPacienteJpaMapper entityMapper) {
		
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}
	@Override
	public Paciente guardar(Paciente nuevoPaciente) {
		PacienteEntity entity=entityMapper.toEntity(nuevoPaciente);
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
	@Override
	
	public Paciente actualizar(String cedula, Paciente paciente) {
		
		PacienteEntity existente = jpaRepositorio.findById(cedula).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
		
		existente.setCedula(paciente.getCedula());
		existente.setApellidos(paciente.getApellidos());
		existente.setCorreo(paciente.getCorreo());
		existente.setDireccion(paciente.getDireccion());
		existente.setNombres(paciente.getNombres());
		existente.setTelefono(paciente.getTelefono());
		existente.setFechaRegistro(paciente.getFechaRegistro());
		
		PacienteEntity guardado = jpaRepositorio.save(existente);
		
		return entityMapper.toDomain(guardado);
	}

		
	
}
