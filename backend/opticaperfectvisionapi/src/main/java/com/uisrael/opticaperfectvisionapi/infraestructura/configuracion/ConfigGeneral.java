package com.uisrael.opticaperfectvisionapi.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.*;
import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl.*;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.*;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores.*;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.*;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.*;

@Configuration
public class ConfigGeneral {

    // Catalogo
    @Bean
    ICatalogoRepositorio catalogoRepositorio(ICatalogoJpaRepositorio repositorio, ICatalogoJpaMapper mapper) {
        return new CatalogoRepositorioImpl(repositorio, mapper);
    }

    @Bean
    ICatalogoUseCase catalogoUseCase(ICatalogoRepositorio repoUseCase) {
        return new CatalogoUseCaseImpl(repoUseCase);
    }

    // Detalle Catalogo
    @Bean
    IDetalleCatalogoRepositorio detalleCatalogoRepositorio(IDetalleCatalogoJpaRepositorio repositorio,
                                                           IDetalleCatalogoJpaMapper mapper) {
        return new DetalleCatalogoRepositorioImpl(repositorio, mapper);
    }

    @Bean
    IDetalleCatalogoUseCase detalleCatalogoUseCase(IDetalleCatalogoRepositorio repoUseCase) {
        return new DetalleCatalogoUseCaseImpl(repoUseCase);
    }

    // Examen Visual
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

    // Usuario Administrador
    @Bean
    IUsuarioAdministradorRepositorio usuarioAdministradorRepositorio(IUsuarioAdministradorJpaRepositorio repositorio,
                                                                     IUsuarioAdministradorJpaMapper mapper,
                                                                     IDetalleCatalogoJpaRepositorio detalleCatalogoRepositorio) {
        return new UsuarioAdministradorRepositorioImpl(repositorio, mapper, detalleCatalogoRepositorio);
    }

    @Bean
    IUsuarioAdministradorUseCase usuarioAdministradorUseCase(IUsuarioAdministradorRepositorio repoUseCase,
                                                             com.uisrael.opticaperfectvisionapi.aplicacion.servicios.IEnvioCorreoService envioCorreoService) {
        return new UsuarioAdministradorUseCaseImpl(repoUseCase, envioCorreoService);
    }

    // Detalle Examen
    @Bean
    IDetalleExamenRepositorio detalleExamenRepositorio(IDetalleExamenJpaRepositorio repositorio,
                                                       IDetalleExamenJpaMapper mapper) {
        return new DetalleExamenRepositorioImpl(repositorio, mapper);
    }

    @Bean
    IDetalleExamenUseCase detalleExamenUseCase(IDetalleExamenRepositorio repoUseCase) {
        return new DetalleExamenUseCaseImpl(repoUseCase);
    }

    // Detalle Entrega
    @Bean
    IDetalleEntregaRepositorio detalleEntregaRepositorio(IDetalleEntregaJpaRepositorio repositorio,
                                                         IDetalleEntregaJpaMapper mapper) {
        return new DetalleEntregaRepositorioImpl(repositorio, mapper);
    }

    @Bean
    IDetalleEntregaUseCase detalleEntregaUseCase(IDetalleEntregaRepositorio repoUseCase) {
        return new DetalleEntregaUseCaseImpl(repoUseCase);
    }

    // Detalle Orden
    @Bean
    IDetalleOrdenRepositorio detalleOrdenRepositorio(IDetalleOrdenJpaRepositorio repositorio,
                                                     IDetalleOrdenJpaMapper mapper) {
        return new DetalleOrdenRepositorioImpl(repositorio, mapper);
    }

    @Bean
    IDetalleOrdenUseCase detalleOrdenUseCase(IDetalleOrdenRepositorio repoUseCase) {
        return new DetalleOrdenUseCaseImpl(repoUseCase);
    }

    // Orden Pedido
    @Bean
    IOrdenPedidoRepositorio ordenPedidoRepositorio(IOrdenPedidoJpaRepositorio repositorio,
                                                   IOrdenPedidoJpaMapper mapper,
                                                   IExamenVisualJpaRepositorio examenVisualRepositorio,
                                                   IPacienteJpaRepositorio pacienteRepositorio,
                                                   IDetalleCatalogoJpaRepositorio detalleCatalogoRepositorio) {
        return new OrdenPedidoRepositorioImpl(repositorio, mapper, examenVisualRepositorio, pacienteRepositorio,
                detalleCatalogoRepositorio);
    }

    @Bean
    IOrdenPedidoUseCase ordenPedidoUseCase(IOrdenPedidoRepositorio repoUseCase) {
        return new OrdenPedidoUseCaseImpl(repoUseCase);
    }

    // Orden Entrega
    @Bean
    IOrdenEntregaRepositorio ordenEntregaRepositorio(IOrdenEntregaJpaRepositorio repositorio,
                                                     IOrdenEntregaJpaMapper mapper) {
        return new OrdenEntregaRepositorioImpl(repositorio, mapper);
    }

    @Bean
    IOrdenEntregaUseCase ordenEntregaUseCase(IOrdenEntregaRepositorio repoUseCase) {
        return new OrdenEntregaUseCaseImpl(repoUseCase);
    }

    // Paciente
    @Bean
    IPacienteRepositorio pacienteRepositorio(IPacienteJpaRepositorio jpaRepositorio,
                                             IPacienteJpaMapper mapper,
                                             IUsuarioAdministradorJpaRepositorio usuarioAdministradorRepositorio) {
        return new PacienteRepositorioImpl(jpaRepositorio, mapper, usuarioAdministradorRepositorio);
    }

    @Bean
    IPacienteUseCase pacienteUseCase(IPacienteRepositorio repositorio) {
        return new PacienteUseCaseImpl(repositorio);
    }
}
