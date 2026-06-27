package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.CatalogoEntity;

public interface ICatalogoJpaRepositorio extends JpaRepository<CatalogoEntity, Integer> {

	boolean existsByDescripcionIgnoreCase(String nombre);
	
	boolean existsByDescripcionIgnoreCaseAndIdCatalogoNot(String descripcion, Integer idCatalogo);
}
