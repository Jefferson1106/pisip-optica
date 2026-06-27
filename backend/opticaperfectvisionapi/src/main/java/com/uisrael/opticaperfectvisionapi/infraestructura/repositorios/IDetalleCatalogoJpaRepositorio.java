package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;

public interface IDetalleCatalogoJpaRepositorio extends JpaRepository<DetalleCatalogoEntity, Integer> {

}
