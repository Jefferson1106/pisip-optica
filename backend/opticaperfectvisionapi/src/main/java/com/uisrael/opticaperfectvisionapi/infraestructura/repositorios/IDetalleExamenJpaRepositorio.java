package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleExamenEntity;

public interface IDetalleExamenJpaRepositorio extends JpaRepository<DetalleExamenEntity, Integer> {
	
	List<DetalleExamenEntity> findByIdDetExamen(Integer idDetExamen);
	
	@Query("Select e from DetalleExamenEntity e")
	List<DetalleExamenEntity> listarTodosDetalleExamen();
	
}
