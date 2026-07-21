package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;

public interface IOrdenEntregaJpaRepositorio extends JpaRepository<OrdenEntregaEntity, Integer> {

    long countByOrdenPedido_IdPedido(Integer idPedido);
	
	//1807
	// Buscar por estado recibido
    List<OrdenEntregaEntity> findByRecibido(Boolean recibido);

    // Buscar por fecha de entrega exacta
    List<OrdenEntregaEntity> findByFechaEntrega(LocalDate fechaEntrega);

    // Buscar por rango de fechas de entrega
    @Query("SELECT o FROM OrdenEntregaEntity o WHERE o.fechaEntrega BETWEEN ?1 AND ?2")
    List<OrdenEntregaEntity> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);

    // Buscar por observaciones (texto parcial)
    @Query("SELECT o FROM OrdenEntregaEntity o WHERE o.observaciones LIKE %?1%")
    List<OrdenEntregaEntity> buscarPorObservaciones(String texto);

    // Listar todas ordenadas por fecha de registro
    @Query("SELECT o FROM OrdenEntregaEntity o ORDER BY o.fechaRegistro DESC")
    List<OrdenEntregaEntity> listarTodosOrdenados();

}
