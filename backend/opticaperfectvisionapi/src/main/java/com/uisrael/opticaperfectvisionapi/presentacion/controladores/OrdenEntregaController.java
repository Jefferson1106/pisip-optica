package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IOrdenEntregaUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenEntrega;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenEntregaRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenEntregaResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IOrdenEntregaDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orden-entregas")
public class OrdenEntregaController {

    private final IOrdenEntregaUseCase ordenEntregaUseCase;
    private final IOrdenEntregaDtoMapper mapper;

    public OrdenEntregaController(IOrdenEntregaUseCase ordenEntregaUseCase, IOrdenEntregaDtoMapper mapper) {
        this.ordenEntregaUseCase = ordenEntregaUseCase;
        this.mapper = mapper;
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<OrdenEntregaResponseDto>> listarTodos() {
        List<OrdenEntregaResponseDto> response = ordenEntregaUseCase.listarTodos().stream()
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenEntregaResponseDto> buscarPorId(@PathVariable int id) {
        OrdenEntrega ordenEntrega = ordenEntregaUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponseDto(ordenEntrega));
    }

    // Crear nueva orden de entrega
    @PostMapping
    public ResponseEntity<OrdenEntregaResponseDto> guardar(@Valid @RequestBody OrdenEntregaRequestDto requestDto) {
        OrdenEntrega guardado = ordenEntregaUseCase.guardar(mapper.toDomain(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
    }

    // Actualizar orden de entrega (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<OrdenEntregaResponseDto> actualizar(@PathVariable int id,
            @Valid @RequestBody OrdenEntregaRequestDto requestDto) {
        OrdenEntrega actualizado = ordenEntregaUseCase.actualizar(id, mapper.toDomain(requestDto));
        return ResponseEntity.ok(mapper.toResponseDto(actualizado));
    }

    // Actualizar solo el campo "recibido" (PATCH)
    @PatchMapping("/{id}/recibido")
    public ResponseEntity<OrdenEntregaResponseDto> actualizarRecibido(@PathVariable int id,
            @RequestBody RecibidoPatchRequest request) {
        if (request == null || request.getRecibido() == null) {
            throw new IllegalArgumentException("El campo 'recibido' es obligatorio");
        }
        OrdenEntrega actualizado = ordenEntregaUseCase.actualizarRecibido(id, request.getRecibido());
        return ResponseEntity.ok(mapper.toResponseDto(actualizado));
    }

    // DTO interno para PATCH
    public static class RecibidoPatchRequest {
        private Boolean recibido;

        public Boolean getRecibido() {
            return recibido;
        }

        public void setRecibido(Boolean recibido) {
            this.recibido = recibido;
        }
    }
    
    //1807

        // Buscar por estado recibido
        @GetMapping("/recibido/{recibido}")
        public ResponseEntity<List<OrdenEntregaResponseDto>> findByRecibido(@PathVariable Boolean recibido) {
            return ResponseEntity.ok(
                ordenEntregaUseCase.findByRecibido(recibido).stream()
                    .map(mapper::toResponseDto).toList()
            );
        }

        // Buscar por fecha de entrega exacta
        @GetMapping("/fecha/{fechaEntrega}")
        public ResponseEntity<List<OrdenEntregaResponseDto>> findByFechaEntrega(@PathVariable LocalDate fechaEntrega) {
            return ResponseEntity.ok(
                ordenEntregaUseCase.findByFechaEntrega(fechaEntrega).stream()
                    .map(mapper::toResponseDto).toList()
            );
        }

        // Buscar por rango de fechas
        @GetMapping("/fechas")
        public ResponseEntity<List<OrdenEntregaResponseDto>> buscarPorRangoFechas(
                @RequestParam LocalDate inicio,
                @RequestParam LocalDate fin) {
            return ResponseEntity.ok(
                ordenEntregaUseCase.buscarPorRangoFechas(inicio, fin).stream()
                    .map(mapper::toResponseDto).toList()
            );
        }

        // Buscar por observaciones (texto parcial)
        @GetMapping("/observaciones/{texto}")
        public ResponseEntity<List<OrdenEntregaResponseDto>> buscarPorObservaciones(@PathVariable String texto) {
            return ResponseEntity.ok(
                ordenEntregaUseCase.buscarPorObservaciones(texto).stream()
                    .map(mapper::toResponseDto).toList()
            );
        }

        // Listar todas ordenadas por fecha de registro
        @GetMapping("/ordenadas")
        public ResponseEntity<List<OrdenEntregaResponseDto>> listarTodosOrdenados() {
            return ResponseEntity.ok(
                ordenEntregaUseCase.listarTodosOrdenados().stream()
                    .map(mapper::toResponseDto).toList()
            );
        }
    

}
