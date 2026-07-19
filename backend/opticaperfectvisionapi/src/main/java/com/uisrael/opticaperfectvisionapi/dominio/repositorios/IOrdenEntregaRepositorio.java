package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;

public interface IOrdenEntregaRepositorio {
	
	OrdenEntrega guardar(OrdenEntrega nuevaOrdenEntrega);
	
	Optional<OrdenEntrega> buscarPorId(int idOrdenEntrega);
	
	List<OrdenEntrega> listarTodos();
	
	void eliminar (int idOrdenEntrega);
	
	//1807
	List<OrdenEntrega> findByRecibido(Boolean recibido);
	List<OrdenEntrega> findByFechaEntrega(LocalDate fechaEntrega);
	List<OrdenEntrega> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
	List<OrdenEntrega> buscarPorObservaciones(String texto);
	List<OrdenEntrega> listarTodosOrdenados();

	
}
