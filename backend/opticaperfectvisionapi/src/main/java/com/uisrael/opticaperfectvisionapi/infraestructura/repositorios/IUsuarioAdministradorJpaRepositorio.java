package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

public interface IUsuarioAdministradorJpaRepositorio extends JpaRepository<UsuarioAdministradorEntity, Integer> {

}
