package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ProductoEntity;

public interface IProductoJpaRepositorio extends JpaRepository<ProductoEntity, Integer> {
	boolean existsByCodigoIgnoreCase(String codigo);
	boolean existsByCodigoIgnoreCaseAndIdProductoNot(String codigo, Integer idProducto);
}
