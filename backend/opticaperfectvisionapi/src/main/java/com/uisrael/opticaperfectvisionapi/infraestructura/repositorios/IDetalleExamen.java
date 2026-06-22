package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleExamenEntity;

public interface IDetalleExamen extends JpaRepository<DetalleExamenEntity, Integer> {

}
