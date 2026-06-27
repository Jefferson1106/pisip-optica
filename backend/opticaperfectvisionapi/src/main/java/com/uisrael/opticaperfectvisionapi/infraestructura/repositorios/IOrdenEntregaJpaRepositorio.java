package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;

public interface IOrdenEntregaJpaRepositorio extends JpaRepository<OrdenEntregaEntity, Integer> {

}
