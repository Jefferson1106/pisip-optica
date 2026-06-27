package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;

public interface IExamenVisualJpaRepositorio extends JpaRepository<ExamenVisualEntity, Integer>{
	

}
