package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

public interface IPacienteJpaRepositorio extends JpaRepository<PacienteEntity, Integer>{

	Optional<PacienteEntity> findByCedula(String cedula);
}
