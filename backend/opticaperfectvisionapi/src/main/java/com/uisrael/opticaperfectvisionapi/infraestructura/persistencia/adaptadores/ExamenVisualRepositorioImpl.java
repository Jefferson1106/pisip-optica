package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IExamenVisualRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IExamenVisualJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IExamenVisualJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IPacienteJpaRepositorio;

public class ExamenVisualRepositorioImpl implements IExamenVisualRepositorio {

	private final IExamenVisualJpaRepositorio jpaRepositorio;
	private final IExamenVisualJpaMapper entityMapper;
	private final IPacienteJpaRepositorio pacienteJpaRepositorio;

	public ExamenVisualRepositorioImpl(IExamenVisualJpaRepositorio jpaRepositorio,
			IExamenVisualJpaMapper entityMapper,
			IPacienteJpaRepositorio pacienteJpaRepositorio) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
		this.pacienteJpaRepositorio = pacienteJpaRepositorio;
	}

	@Override
	public ExamenVisual guardar(ExamenVisual nuevoExamenVisual) {
		ExamenVisualEntity entity = entityMapper.toEntity(nuevoExamenVisual);
		entity.setPaciente(obtenerPacienteAdministrado(entity.getPaciente()));
		ExamenVisualEntity guardado = jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<ExamenVisual> buscarPorId(int idExamen) {
		return jpaRepositorio.findByIdWithPaciente(idExamen).map(entityMapper::toDomain);
	}

	@Override
	public List<ExamenVisual> listarTodos() {
		return jpaRepositorio.findAllWithPaciente().stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public ExamenVisual actualizar(int id, ExamenVisual examenVisual) {
		ExamenVisualEntity existente = jpaRepositorio.findByIdWithPaciente(id)
				.orElseThrow(() -> new RuntimeException("Examen visual no encontrado"));

		existente.setPaciente(obtenerPacienteAdministrado(examenVisual.getPaciente()));
		existente.setFechaExamen(examenVisual.getFechaExamen());
		existente.setObservaciones(examenVisual.getObservaciones());
		existente.setEstado(examenVisual.isEstado());

		ExamenVisualEntity guardado = jpaRepositorio.save(existente);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public ExamenVisual actualizarEstado(int id, ExamenVisual examenVisual) {
		ExamenVisualEntity existente = jpaRepositorio.findByIdWithPaciente(id)
				.orElseThrow(() -> new RuntimeException("Examen visual no encontrado"));

		existente.setEstado(examenVisual.isEstado());

		ExamenVisualEntity guardado = jpaRepositorio.save(existente);
		return entityMapper.toDomain(guardado);
	}

	private PacienteEntity obtenerPacienteAdministrado(PacienteEntity paciente) {
		if (paciente == null || paciente.getCedula() == null || paciente.getCedula().isBlank()) {
			throw new RuntimeException("La cédula del paciente es obligatoria");
		}

		return pacienteJpaRepositorio.findById(paciente.getCedula())
				.orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
	}
}
