package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;

public interface IOrdenPedidoJpaRepositorio extends JpaRepository<OrdenPedidoEntity, Integer> {

}
