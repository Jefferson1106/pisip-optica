package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

public interface IUsuarioAdministradorRepository extends JpaRepository<UsuarioAdministradorEntity, Integer> {

}
