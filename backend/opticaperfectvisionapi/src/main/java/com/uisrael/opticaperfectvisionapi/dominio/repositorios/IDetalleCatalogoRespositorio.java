package com.uisrael.opticaperfectvisionapi.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;

public interface IDetalleCatalogoRespositorio {
	DetalleCatalogo guardar(DetalleCatalogo nuevoDetalleCatalogo);
	Optional<DetalleCatalogo> buscarPorId(int idDetalleCatalogo);
	List<DetalleCatalogo> listarTodos();
	void eliminar (int idDetalleCatalogo);

}
