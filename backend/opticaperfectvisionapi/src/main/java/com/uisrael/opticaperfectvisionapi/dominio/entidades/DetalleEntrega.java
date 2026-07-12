package com.uisrael.opticaperfectvisionapi.dominio.entidades;

import java.time.LocalDateTime;

public class DetalleEntrega {

    private Integer idDetEntrega;
    private Integer cantidad;
    private Boolean estado;
    private LocalDateTime fechaRegistro;

    // Relaciones (FK)
    private OrdenEntrega ordenEntrega;       // en vez de idEntrega
    private DetalleCatalogo productoCatalogo; // en vez de idProducto

    public DetalleEntrega() {}

    public DetalleEntrega(Integer idDetEntrega, Integer cantidad, Boolean estado, LocalDateTime fechaRegistro,
                          OrdenEntrega ordenEntrega, DetalleCatalogo productoCatalogo) {
        this.idDetEntrega = idDetEntrega;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.ordenEntrega = ordenEntrega;
        this.productoCatalogo = productoCatalogo;
    }

    public Integer getIdDetEntrega() {
        return idDetEntrega;
    }

    public void setIdDetEntrega(Integer idDetEntrega) {
        this.idDetEntrega = idDetEntrega;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public OrdenEntrega getOrdenEntrega() {
        return ordenEntrega;
    }

    public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
        this.ordenEntrega = ordenEntrega;
    }

    public DetalleCatalogo getProductoCatalogo() {
        return productoCatalogo;
    }

    public void setProductoCatalogo(DetalleCatalogo productoCatalogo) {
        this.productoCatalogo = productoCatalogo;
    }
}
