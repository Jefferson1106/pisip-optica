package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;

public interface IDetalleOrdenUseCase {
	
	DetalleOrden guardar(DetalleOrden nuevoDetalleOrden);
	
	DetalleOrden buscarPorId(int idDetalleOrden);
	
	List<DetalleOrden> listarTodos();
	
	DetalleOrden actualizar(int idDetalleOrden, DetalleOrden detalleOrden);
	
}
