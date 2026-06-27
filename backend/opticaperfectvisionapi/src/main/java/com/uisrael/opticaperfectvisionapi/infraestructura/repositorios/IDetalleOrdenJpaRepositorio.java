package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;

public interface IDetalleOrdenJpaRepositorio extends JpaRepository<DetalleOrdenEntity, Integer> {

}
