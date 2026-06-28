package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

public interface IUsuarioAdministradorJpaRepositorio extends JpaRepository<UsuarioAdministradorEntity, Integer> {

	Optional<UsuarioAdministradorEntity> findByCorreoIgnoreCase(String correo);

	boolean existsByCorreoIgnoreCase(String correo);

	boolean existsByCorreoIgnoreCaseAndIdUsuarioNot(String correo, Integer idUsuario);
}