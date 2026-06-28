package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;

public interface IDetalleCatalogoRepositorio {

	DetalleCatalogo guardar(DetalleCatalogo nuevoDetalleCatalogo);

	Optional<DetalleCatalogo> buscarPorId(int idDetalleCatalogo);

	List<DetalleCatalogo> listarTodos();

	DetalleCatalogo actualizar(int id, DetalleCatalogo detalleCatalogo);

	DetalleCatalogo actualizarEstado(int id, DetalleCatalogo detalleCatalogo);

	boolean existeNombre(String nombre);

	boolean existeNombreParaOtro(String nombre, int idDetalleCatalogo);
}
