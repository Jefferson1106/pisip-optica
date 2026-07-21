package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenEntregaResponseDto;

public interface IOrdenEntregaService {
    
    // Listar todas las órdenes
    List<OrdenEntregaResponseDto> listarOrdenes();

    // Guardar nueva orden
    void guardarOrden(OrdenEntregaRequestDto nueva);

    // Buscar orden por ID (para ver detalle o editar)
    OrdenEntregaResponseDto buscarPorId(Integer idEntrega);

    // Actualizar datos de la orden
    void actualizarOrden(Integer idEntrega, OrdenEntregaRequestDto orden);

    // Eliminar orden (borrado lógico)
    void eliminarOrden(Integer idEntrega);
}
