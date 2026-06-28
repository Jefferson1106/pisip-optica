package com.uisrael.opticaperfectvisionapi.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.ICatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleCatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IExamenVisualUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.CatalogoUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.DetalleCatalogoUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.ExamenVisualUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.ICatalogoRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleCatalogoRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IExamenVisualRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.CatalogoRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.DetalleCatalogoRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.ExamenVisualRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.ICatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleCatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IExamenVisualJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.ICatalogoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleCatalogoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IExamenVisualJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IPacienteJpaRepositorio;

@Configuration
public class ConfigGeneral {

	@Bean
	ICatalogoRepositorio catalogoRepositorio(ICatalogoJpaRepositorio repositorio, ICatalogoJpaMapper mapper) {
		return new CatalogoRepositorioImpl(repositorio, mapper);
	}

	@Bean
	ICatalogoUseCase catalogouse(ICatalogoRepositorio repoUseCase) {
		return new CatalogoUseCaseImpl(repoUseCase);
	}

	@Bean
	IDetalleCatalogoRepositorio detalleCatalogoRepositorio(IDetalleCatalogoJpaRepositorio repositorio,
			IDetalleCatalogoJpaMapper mapper) {
		return new DetalleCatalogoRepositorioImpl(repositorio, mapper);
	}

	@Bean
	IDetalleCatalogoUseCase detalleCatalogoUseCase(IDetalleCatalogoRepositorio repoUseCase) {
		return new DetalleCatalogoUseCaseImpl(repoUseCase);
	}

	@Bean
	IExamenVisualRepositorio examenVisualRepositorio(IExamenVisualJpaRepositorio repositorio,
			IExamenVisualJpaMapper mapper,
			IPacienteJpaRepositorio pacienteRepositorio) {
		return new ExamenVisualRepositorioImpl(repositorio, mapper, pacienteRepositorio);
	}

	@Bean
	IExamenVisualUseCase examenVisualUseCase(IExamenVisualRepositorio repoUseCase) {
		return new ExamenVisualUseCaseImpl(repoUseCase);
	}
}
