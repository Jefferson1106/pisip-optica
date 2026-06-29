package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleEntregaUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleEntrega;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleEntregaResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IDetalleEntregaDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detalle-entregas")
public class DetalleEntregaController {

    private final IDetalleEntregaUseCase detalleEntregaUseCase;
    private final IDetalleEntregaDtoMapper mapper;

    public DetalleEntregaController(IDetalleEntregaUseCase detalleEntregaUseCase, IDetalleEntregaDtoMapper mapper) {
        this.detalleEntregaUseCase = detalleEntregaUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<DetalleEntregaResponseDto>> listarTodos() {
        List<DetalleEntregaResponseDto> response = detalleEntregaUseCase.listarTodos().stream()
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleEntregaResponseDto> buscarPorId(@PathVariable int id) {
        DetalleEntrega detalleEntrega = detalleEntregaUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponseDto(detalleEntrega));
    }

    @PostMapping
    public ResponseEntity<DetalleEntregaResponseDto> guardar(
            @Valid @RequestBody DetalleEntregaRequestDto requestDto) {
        DetalleEntrega guardado = detalleEntregaUseCase.guardar(mapper.toDomain(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleEntregaResponseDto> actualizar(@PathVariable int id,
            @Valid @RequestBody DetalleEntregaRequestDto requestDto) {
        DetalleEntrega actualizado = detalleEntregaUseCase.actualizar(id, mapper.toDomain(requestDto));
        return ResponseEntity.ok(mapper.toResponseDto(actualizado));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<DetalleEntregaResponseDto> actualizarEstado(@PathVariable int id,
            @RequestParam(required = false) Boolean estado,
            @RequestBody(required = false) EstadoPatchRequest request) {
        Boolean estadoFinal = estado != null ? estado : (request != null ? request.getEstado() : null);
        if (estadoFinal == null) {
            throw new IllegalArgumentException("El campo estado es obligatorio");
        }
        DetalleEntrega actualizado = detalleEntregaUseCase.actualizarEstado(id, estadoFinal);
        return ResponseEntity.ok(mapper.toResponseDto(actualizado));
    }

    private static class EstadoPatchRequest {
        private Boolean estado;

        public Boolean getEstado() {
            return estado;
        }

        public void setEstado(Boolean estado) {
            this.estado = estado;
        }
    }
}
