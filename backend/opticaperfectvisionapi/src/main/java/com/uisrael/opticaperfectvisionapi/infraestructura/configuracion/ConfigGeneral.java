package com.uisrael.opticaperfectvisionapi.infraestructura.configuracion;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.CatalogoRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.ICatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.ICatalogoJpaRepositorio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.ICatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.CatalogoUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.ICatalogoRepositorio;

@Configuration
public class ConfigGeneral {

	@Bean
	ICatalogoRepositorio catalogoRepositorio(ICatalogoJpaRepositorio repositorio,ICatalogoJpaMapper mapper) {
		return new CatalogoRepositorioImpl(repositorio, mapper);
	}
	
	@Bean
	ICatalogoUseCase catalogouse(ICatalogoRepositorio repoUseCase){
		return new CatalogoUseCaseImpl(repoUseCase);
	}
}
