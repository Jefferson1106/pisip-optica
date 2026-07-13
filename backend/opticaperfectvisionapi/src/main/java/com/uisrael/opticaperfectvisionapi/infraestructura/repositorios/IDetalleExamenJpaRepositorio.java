package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleExamenEntity;

public interface IDetalleExamenJpaRepositorio extends JpaRepository<DetalleExamenEntity, Integer> {

	Optional<DetalleExamenEntity> findByExamenVisual_IdExamen(Integer idExamen);

}
