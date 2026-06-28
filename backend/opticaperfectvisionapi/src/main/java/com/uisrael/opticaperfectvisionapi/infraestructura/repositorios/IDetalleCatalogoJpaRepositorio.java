package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;

public interface IDetalleCatalogoJpaRepositorio extends JpaRepository<DetalleCatalogoEntity, Integer> {

	boolean existsByNombreIgnoreCase(String nombre);

	boolean existsByNombreIgnoreCaseAndIdDetalleCatalogoNot(String nombre, Integer idDetalleCatalogo);

	@Query("SELECT d FROM DetalleCatalogoEntity d JOIN FETCH d.catalogo WHERE d.idDetalleCatalogo = :id")
	Optional<DetalleCatalogoEntity> findByIdWithCatalogo(@Param("id") Integer id);

	@Query("SELECT d FROM DetalleCatalogoEntity d JOIN FETCH d.catalogo")
	List<DetalleCatalogoEntity> findAllWithCatalogo();
}
