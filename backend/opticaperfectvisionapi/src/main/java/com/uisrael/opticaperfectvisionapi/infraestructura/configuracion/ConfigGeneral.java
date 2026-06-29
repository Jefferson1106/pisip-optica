package com.uisrael.opticaperfectvisionapi.infraestructura.configuracion;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleCatalogoJpaMapperImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleEntregaJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IOrdenPedidoJpaRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.ICatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleCatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleEntregaUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleExamenUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleOrdenUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IExamenVisualUseCase;
<<<<<<< HEAD
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IOrdenEntregaUseCase;
=======
>>>>>>> 016dbd61e73b14b5e377769b2916b884c0d61bb8
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IOrdenPedidoUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IUsuarioAdministradorUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.CatalogoUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.DetalleCatalogoUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.DetalleEntregaUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.DetalleExamenUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.DetalleOrdenUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.ExamenVisualUseCaseImpl;
<<<<<<< HEAD
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.OrdenEntregaUseCaseImpl;
=======
>>>>>>> 016dbd61e73b14b5e377769b2916b884c0d61bb8
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.OrdenPedidoUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.UsuarioAdministradorUseCaseImpl;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.ICatalogoRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleCatalogoRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleEntregaRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleExamenRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleOrdenRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IExamenVisualRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenEntregaRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenPedidoRepositorio;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IUsuarioAdministradorRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.CatalogoRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.DetalleCatalogoRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.DetalleEntregaRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.DetalleExamenRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.DetalleOrdenRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.ExamenVisualRepositorioImpl;
<<<<<<< HEAD
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.OrdenEntregaRepositorioImpl;
=======
>>>>>>> 016dbd61e73b14b5e377769b2916b884c0d61bb8
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.OrdenPedidoRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.UsuarioAdministradorRepositorioImpl;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.ICatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleCatalogoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleExamenJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IDetalleOrdenJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IExamenVisualJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IOrdenEntregaJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IOrdenPedidoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IUsuarioAdministradorJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.ICatalogoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleCatalogoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleEntregaJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleExamenJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleOrdenJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IExamenVisualJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IOrdenEntregaJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IPacienteJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IUsuarioAdministradorJpaRepositorio;

@Configuration
public class ConfigGeneral {

	private final IDetalleCatalogoJpaMapperImpl IDetalleCatalogoJpaMapperImpl;
	private final IOrdenPedidoJpaRepositorio IOrdenPedidoJpaRepositorio;

	ConfigGeneral(IOrdenPedidoJpaRepositorio IOrdenPedidoJpaRepositorio, IDetalleCatalogoJpaMapperImpl IDetalleCatalogoJpaMapperImpl) {
		this.IOrdenPedidoJpaRepositorio = IOrdenPedidoJpaRepositorio;
		this.IDetalleCatalogoJpaMapperImpl = IDetalleCatalogoJpaMapperImpl;
	}

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

	@Bean
	IUsuarioAdministradorRepositorio usuarioAdministradorRepositorio(IUsuarioAdministradorJpaRepositorio repositorio,
			IUsuarioAdministradorJpaMapper mapper, IDetalleCatalogoJpaRepositorio detalleCatalogoRepositorio) {
		return new UsuarioAdministradorRepositorioImpl(repositorio, mapper, detalleCatalogoRepositorio);
	}

	@Bean
	IUsuarioAdministradorUseCase usuarioAdministradorUseCase(IUsuarioAdministradorRepositorio repoUseCase,
			com.uisrael.opticaperfectvisionapi.aplicacion.servicios.IEnvioCorreoService envioCorreoService) {
		return new UsuarioAdministradorUseCaseImpl(repoUseCase, envioCorreoService);
	}
	
	@Bean
	IDetalleExamenRepositorio detalleExamenRepositorio(IDetalleExamenJpaRepositorio detalleExamenRepositorio,
			IDetalleExamenJpaMapper mapper, IDetalleExamenJpaRepositorio examenRepositorio) {
		return new DetalleExamenRepositorioImpl(examenRepositorio, mapper);
	}
	
	@Bean
	IDetalleExamenUseCase detalleExamenUseCase(IDetalleExamenRepositorio repoUseCase) {
		return new DetalleExamenUseCaseImpl(repoUseCase);
	}
	
	@Bean
<<<<<<< HEAD
	IDetalleEntregaRepositorio detalleEntregaRepositorio(IDetalleEntregaJpaRepositorio repositorio,
	                                                     IDetalleEntregaJpaMapper mapper) {
	    return new DetalleEntregaRepositorioImpl(repositorio, mapper);
	}

	@Bean
	IDetalleEntregaUseCase detalleEntregaUseCase(IDetalleEntregaRepositorio repoUseCase) {
	    return new DetalleEntregaUseCaseImpl(repoUseCase);
	}

	@Bean
	IDetalleOrdenRepositorio detalleOrdenRepositorio(IDetalleOrdenJpaRepositorio repositorio,
	                                                 IDetalleOrdenJpaMapper mapper) {
	    return new DetalleOrdenRepositorioImpl(repositorio, mapper);
	}

	@Bean
	IDetalleOrdenUseCase detalleOrdenUseCase(IDetalleOrdenRepositorio repoUseCase) {
	    return new DetalleOrdenUseCaseImpl(repoUseCase);
	}

	@Bean
	IOrdenPedidoRepositorio ordenPedidoRepositorio(IOrdenPedidoJpaRepositorio repositorio,
	                                               IOrdenPedidoJpaMapper mapper) {
	    return new OrdenPedidoRepositorioImpl(repositorio, mapper);
	}

	@Bean
	IOrdenPedidoUseCase ordenPedidoUseCase(IOrdenPedidoRepositorio repoUseCase) {
	    return new OrdenPedidoUseCaseImpl(repoUseCase);
	}
	

	@Bean
    IOrdenEntregaRepositorio ordenEntregaRepositorio(IOrdenEntregaJpaRepositorio repositorio,
                                                     IOrdenEntregaJpaMapper mapper) {
        return new OrdenEntregaRepositorioImpl(repositorio, mapper);
    }

    // Bean del caso de uso de OrdenEntrega
    @Bean
    IOrdenEntregaUseCase ordenEntregaUseCase(IOrdenEntregaRepositorio repoUseCase) {
        return new OrdenEntregaUseCaseImpl(repoUseCase);
    }


=======
	IOrdenPedidoRepositorio ordenPedidoRepositorio(IOrdenPedidoJpaRepositorio ordenPedidoRepositorio,
			IOrdenPedidoJpaMapper mapper, IOrdenPedidoJpaRepositorio ordenPedido) {
		return new OrdenPedidoRepositorioImpl(ordenPedidoRepositorio, mapper);
	}
	
	@Bean
	IOrdenPedidoUseCase ordenPedidoUseCase(IOrdenPedidoRepositorio repoUseCase) {
		return new OrdenPedidoUseCaseImpl(repoUseCase);
	}
	
	@Bean
	IDetalleOrdenRepositorio detalleOrdenRespositorio(IDetalleOrdenJpaRepositorio detalleOrdenRepositorio,
			IDetalleOrdenJpaMapper mapper, IDetalleOrdenJpaRepositorio detalleOrden) {
		return new DetalleOrdenRepositorioImpl(detalleOrdenRepositorio, mapper);
	}
	
	@Bean
	IDetalleOrdenUseCase detalleOrdenUseCase(IDetalleOrdenRepositorio repoUseCase) {
		return new DetalleOrdenUseCaseImpl(repoUseCase);
		
	}
>>>>>>> 016dbd61e73b14b5e377769b2916b884c0d61bb8
}
