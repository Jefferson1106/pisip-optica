package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.time.LocalDate;
import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;

public interface IOrdenEntregaUseCase {

	OrdenEntrega guardar(OrdenEntrega nuevaOrdenEntrega);
	OrdenEntrega buscarPorId(int idOrdenEntrega);
	List<OrdenEntrega> listarTodos();
	// Nuevo: actualizar todos los campos
    OrdenEntrega actualizar(int idOrdenEntrega, OrdenEntrega ordenEntregaActualizada);
        // Nuevo: actualizar solo el campo "recibido"
    OrdenEntrega actualizarRecibido(int idOrdenEntrega, Boolean recibido);
    
    //1807
    
    List<OrdenEntrega> findByRecibido(Boolean recibido);

    List<OrdenEntrega> findByFechaEntrega(LocalDate fechaEntrega);

    List<OrdenEntrega> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);

    List<OrdenEntrega> buscarPorObservaciones(String texto);

    List<OrdenEntrega> listarTodosOrdenados();
      
}
