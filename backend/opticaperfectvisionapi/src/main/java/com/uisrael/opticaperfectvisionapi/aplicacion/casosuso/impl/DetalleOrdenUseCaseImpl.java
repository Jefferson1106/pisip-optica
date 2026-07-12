package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleOrdenUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleOrdenRepositorio;


public class DetalleOrdenUseCaseImpl implements IDetalleOrdenUseCase {

    private final IDetalleOrdenRepositorio repositorio;

    public DetalleOrdenUseCaseImpl(IDetalleOrdenRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public DetalleOrden guardar(DetalleOrden nuevoDetalleOrden) {
        return repositorio.guardar(nuevoDetalleOrden);
    }

    @Override
    public DetalleOrden buscarPorId(int idDetalleOrden) {
        return repositorio.buscarPorId(idDetalleOrden)
                .orElseThrow(() -> new RuntimeException("Detalle orden no encontrado"));
    }

    @Override
    public List<DetalleOrden> listarTodos() {
        return repositorio.listarTodos();
    }

    @Override
    public void eliminar(int idDetalleOrden) {
        repositorio.eliminar(idDetalleOrden);
    }

    @Override
    public DetalleOrden actualizar(int idDetalleOrden, DetalleOrden detalleOrden) {
        DetalleOrden existente = repositorio.buscarPorId(idDetalleOrden)
                .orElseThrow(() -> new RuntimeException("Detalle orden no encontrado"));

        existente.setCantidad(detalleOrden.getCantidad());
        existente.setEstado(detalleOrden.getEstado());
        existente.setFechaRegistro(detalleOrden.getFechaRegistro());
        existente.setIdProducto(detalleOrden.getIdProducto());

        return repositorio.guardar(existente);
    }
}
