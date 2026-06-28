package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;

public interface IExamenVisualJpaRepositorio extends JpaRepository<ExamenVisualEntity, Integer> {

	@Query("SELECT e FROM ExamenVisualEntity e JOIN FETCH e.paciente WHERE e.idExamen = :id")
	Optional<ExamenVisualEntity> findByIdWithPaciente(@Param("id") Integer id);

	@Query("SELECT e FROM ExamenVisualEntity e JOIN FETCH e.paciente")
	List<ExamenVisualEntity> findAllWithPaciente();
}
