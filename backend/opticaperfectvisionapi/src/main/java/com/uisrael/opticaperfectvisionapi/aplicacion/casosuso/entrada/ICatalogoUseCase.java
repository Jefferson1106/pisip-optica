package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;

public interface ICatalogoUseCase {

	Catalogo guardar(Catalogo nuevoCatalogo);

	Catalogo buscarPorId(int idCatalogo);

	List<Catalogo> listarTodos();
	
	Catalogo actualizar(int id, Catalogo catalogo);
	
	boolean existeDescripcion(String descripcion);
	
	Catalogo actualizarEstado(int id, boolean estado);
}
