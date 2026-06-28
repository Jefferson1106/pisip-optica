package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;

public interface IDetalleCatalogoUseCase {

	DetalleCatalogo guardar(DetalleCatalogo nuevoDetalleCatalogo);

	DetalleCatalogo buscarPorId(int idDetalleCatalogo);

	List<DetalleCatalogo> listarTodos();

	DetalleCatalogo actualizar(int id, DetalleCatalogo detalleCatalogo);

	boolean existeNombre(String nombre);

	DetalleCatalogo actualizarEstado(int id, boolean estado);
}
