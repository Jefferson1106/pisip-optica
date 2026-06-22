package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleEntregaEntity;

public interface IDetalleEntrega extends JpaRepository<DetalleEntregaEntity, Integer> {

}
