package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;

public interface IDetalleOrdenJpaRepositorio extends JpaRepository<DetalleOrdenEntity, Integer> {
	
	List<DetalleOrdenEntity> findByIdDetOrden(Integer idDetOrden);

	long countByOrdenPedido_IdPedido(Integer idPedido);
	
	@Query("Select o from DetalleOrdenEntity o")
	List<DetalleOrdenEntity> listarTodosDetalleOrden();
	
}
