package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.CatalogoEntity;

public interface ICatalogoRepository extends JpaRepository<CatalogoEntity, Integer> {

}
