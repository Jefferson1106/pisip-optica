package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ProveedorEntity;

public interface IProveedorJpaRepositorio extends JpaRepository<ProveedorEntity, Integer> {
	boolean existsByNombreIgnoreCase(String nombre);
	boolean existsByNombreIgnoreCaseAndIdProveedorNot(String nombre, Integer idProveedor);
	boolean existsByIdentificacion(String identificacion);
	boolean existsByIdentificacionAndIdProveedorNot(String identificacion, Integer idProveedor);
}
