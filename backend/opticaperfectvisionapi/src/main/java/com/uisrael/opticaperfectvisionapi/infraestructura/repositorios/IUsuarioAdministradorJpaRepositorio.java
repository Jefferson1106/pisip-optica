package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;

public interface IUsuarioAdministradorJpaRepositorio extends JpaRepository<UsuarioAdministradorEntity, Integer> {

	@Query("SELECT u FROM UsuarioAdministradorEntity u JOIN FETCH u.tipoUsuario")
	List<UsuarioAdministradorEntity> findAllConTipoUsuario();

	@Query("SELECT u FROM UsuarioAdministradorEntity u JOIN FETCH u.tipoUsuario WHERE u.idUsuario = ?1")
	Optional<UsuarioAdministradorEntity> findByIdConTipoUsuario(Integer id);

	Optional<UsuarioAdministradorEntity> findByCorreoIgnoreCase(String correo);

	boolean existsByCorreoIgnoreCase(String correo);

	boolean existsByCorreoIgnoreCaseAndIdUsuarioNot(String correo, Integer idUsuario);
}