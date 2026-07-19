package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;
import java.util.List;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;

public interface IDetalleEntregaUseCase {
    DetalleEntrega guardar(DetalleEntrega nuevoDetalleEntrega);
    DetalleEntrega buscarPorId(int idDetalleEntrega);
    List<DetalleEntrega> listarTodos();
    void eliminar(int idDetalleEntrega);
    DetalleEntrega actualizar(int idDetalleEntrega, DetalleEntrega detalleEntrega);
    DetalleEntrega actualizarEstado(int idDetalleEntrega, Boolean estado);
    
    
    //1807
    List<DetalleEntrega> findByEstado(Boolean estado);
    List<DetalleEntrega> findByCantidad(Integer cantidad);
    List<DetalleEntrega> buscarPorProductoYEstado(Integer idDetOrden, Boolean estado);
    List<DetalleEntrega> listarTodosOrdenados();

}
