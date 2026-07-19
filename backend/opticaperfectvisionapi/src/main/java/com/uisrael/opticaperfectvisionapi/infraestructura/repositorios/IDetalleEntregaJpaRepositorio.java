package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleEntregaEntity;

public interface IDetalleEntregaJpaRepositorio extends JpaRepository<DetalleEntregaEntity, Integer> {

	//1807
    // Buscar por estado
    List<DetalleEntregaEntity> findByEstado(Boolean estado);

    // Buscar por cantidad
    List<DetalleEntregaEntity> findByCantidad(Integer cantidad);

    // Consulta personalizada: buscar por producto y estado
    @Query("SELECT d FROM DetalleEntregaEntity d WHERE d.producto.idDetOrden = ?1 AND d.estado = ?2")
    List<DetalleEntregaEntity> buscarPorProductoYEstado(Integer idDetOrden, Boolean estado);

    // Consulta personalizada: listar todos ordenados por fecha
    @Query("SELECT d FROM DetalleEntregaEntity d ORDER BY d.fechaRegistro DESC")
    List<DetalleEntregaEntity> listarTodosOrdenados();
}
