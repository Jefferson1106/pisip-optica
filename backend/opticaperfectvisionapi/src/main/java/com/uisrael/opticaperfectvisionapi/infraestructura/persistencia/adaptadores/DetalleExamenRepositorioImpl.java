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
		if (entity.getExamenVisual() != null && entity.getExamenVisual().getIdExamen() != null) {
			jpaRepositorio.findByExamenVisual_IdExamen(entity.getExamenVisual().getIdExamen())
					.ifPresent(existente -> entity.setIdDetExamen(existente.getIdDetExamen()));
		}
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
	@Override
	public boolean existeDescripcion(String descripcionExamen) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public DetalleExamen actualizar(int idDetalleExamen, DetalleExamen detalleExamen) {
		DetalleExamenEntity existente = jpaRepositorio.findById(idDetalleExamen).orElseThrow(() -> new RuntimeException("Detalle examen no encontrado"));
		
		existente.setExamenVisual(detalleExamen.getExamenVisual());
		existente.setEsferaDistanciaOd(detalleExamen.getEsferaDistanciaOd());
		existente.setCilindroDistanciaOd(detalleExamen.getCilindroDistanciaOd());
		existente.setEjeDistanciaOd(detalleExamen.getEjeDistanciaOd());
		existente.setEsferaDistanciaOi(detalleExamen.getEsferaDistanciaOi());
		existente.setCilindroDistanciaOi(detalleExamen.getCilindroDistanciaOi());
		existente.setEjeDistanciaOi(detalleExamen.getEjeDistanciaOi());
		existente.setAdicionOd(detalleExamen.getAdicionOd());
		existente.setAdicionOi(detalleExamen.getAdicionOi());
		existente.setDistanciaPupilar(detalleExamen.getDistanciaPupilar());
		existente.setAlturaBifocal(detalleExamen.getAlturaBifocal());
		existente.setAlturaProgresivo(detalleExamen.getAlturaProgresivo());
		existente.setEsferaLecturaOd(detalleExamen.getEsferaLecturaOd());
		existente.setCilindroLecturaOd(detalleExamen.getCilindroLecturaOd());
		existente.setEjeLecturaOd(detalleExamen.getEjeLecturaOd());
		existente.setEsferaLecturaOi(detalleExamen.getEsferaLecturaOi());
		existente.setCilindroLecturaOi(detalleExamen.getCilindroLecturaOi());
		existente.setEjeLecturaOi(detalleExamen.getEjeLecturaOi());
		
		DetalleExamenEntity guardado = jpaRepositorio.save(existente);
		
		return entityMapper.toDomain(guardado);
	}
	
	

}
