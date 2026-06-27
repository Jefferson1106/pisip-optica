package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;

public interface ICatalogoRepositorio {

	Catalogo guardar(Catalogo nuevoCatalogo);

	Optional<Catalogo> buscarPorId(int idCatalogo);

	List<Catalogo> listarTodos();
	
	Catalogo actualizar(int id, Catalogo catalogo);
	
	Catalogo actualizarEstado(int id, Catalogo cargo);
	
	boolean existeDescripcion(String descripcion);
	
	boolean existeNombreParaOtro(String nombre, int idCargo);
}
