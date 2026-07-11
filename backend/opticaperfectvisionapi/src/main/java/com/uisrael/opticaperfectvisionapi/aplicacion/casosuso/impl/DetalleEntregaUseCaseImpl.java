package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleEntregaUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleEntregaRepositorio;

public class DetalleEntregaUseCaseImpl implements IDetalleEntregaUseCase {

    private final IDetalleEntregaRepositorio repositorio;

    public DetalleEntregaUseCaseImpl(IDetalleEntregaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public DetalleEntrega guardar(DetalleEntrega nuevoDetalleEntrega) {
        return repositorio.guardar(nuevoDetalleEntrega);
    }

    @Override
    public DetalleEntrega buscarPorId(int idDetalleEntrega) {
        return repositorio.buscarPorId(idDetalleEntrega)
                .orElseThrow(() -> new RuntimeException("Detalle entrega no encontrado"));
    }

    @Override
    public List<DetalleEntrega> listarTodos() {
        return repositorio.listarTodos();
    }

    @Override
    public void eliminar(int idDetalleEntrega) {
        repositorio.eliminar(idDetalleEntrega);
    }

    @Override
    public DetalleEntrega actualizar(int idDetalleEntrega, DetalleEntrega detalleEntrega) {
        DetalleEntrega existente = repositorio.buscarPorId(idDetalleEntrega)
                .orElseThrow(() -> new RuntimeException("Detalle entrega no encontrado"));

        existente.setCantidad(detalleEntrega.getCantidad());
        existente.setFechaRegistro(detalleEntrega.getFechaRegistro());
        existente.setProducto(detalleEntrega.getProducto());
        existente.setOrdenEntrega(detalleEntrega.getOrdenEntrega());
        existente.setEstado(detalleEntrega.getEstado());

        return repositorio.guardar(existente);
    }

    @Override
    public DetalleEntrega actualizarEstado(int idDetalleEntrega, Boolean estado) {
        DetalleEntrega existente = repositorio.buscarPorId(idDetalleEntrega)
                .orElseThrow(() -> new RuntimeException("Detalle entrega no encontrado"));
        existente.setEstado(estado);
        return repositorio.guardar(existente);
    }
}
