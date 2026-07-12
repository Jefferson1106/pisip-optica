package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;

public interface IOrdenPedidoJpaRepositorio extends JpaRepository<OrdenPedidoEntity, Integer> {

	@Query("SELECT o FROM OrdenPedidoEntity o "
			+ "JOIN FETCH o.examenVisual "
			+ "JOIN FETCH o.paciente "
			+ "JOIN FETCH o.estadoPedido "
			+ "WHERE o.idPedido = :id")
	Optional<OrdenPedidoEntity> findByIdWithRelaciones(@Param("id") Integer id);

	@Query("SELECT o FROM OrdenPedidoEntity o "
			+ "JOIN FETCH o.examenVisual "
			+ "JOIN FETCH o.paciente "
			+ "JOIN FETCH o.estadoPedido")
	List<OrdenPedidoEntity> findAllWithRelaciones();

}
