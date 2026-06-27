package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;


import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;

public interface IOrdenEntregaUseCase {
	
	OrdenEntrega guardar(OrdenEntrega nuevaOrdenEntrega);
	
	OrdenEntrega buscarPorId(int idOrdenEntrega);
	
	List<OrdenEntrega> listarTodos();
	
}
