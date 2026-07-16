package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.PacienteResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IPacienteDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final IPacienteUseCase pacienteUseCase;
    private final IPacienteDtoMapper mapper;

    public PacienteController(IPacienteUseCase pacienteUseCase, IPacienteDtoMapper mapper) {
        this.pacienteUseCase = pacienteUseCase;
        this.mapper = mapper;
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<PacienteResponseDto>> listarTodos() {
        List<PacienteResponseDto> pacientes = pacienteUseCase.listarTodos().stream()
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(pacientes);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@PathVariable int id) {
        Paciente paciente = pacienteUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponseDto(paciente));
    }

    // Guardar nuevo paciente
    @PostMapping
    public ResponseEntity<PacienteResponseDto> guardar(@Valid @RequestBody PacienteRequestDto requestDto) {
        validarIdUsuarioRegistro(requestDto);
        Paciente guardado = pacienteUseCase.guardar(mapper.toDomain(requestDto));
        PacienteResponseDto responseDto = mapper.toResponseDto(guardado);
        if (responseDto.getIdUsuarioRegistro() == null) {
            responseDto.setIdUsuarioRegistro(requestDto.getIdUsuarioRegistro());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    private void validarIdUsuarioRegistro(PacienteRequestDto requestDto) {
        if (requestDto.getIdUsuarioRegistro() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "idUsuarioRegistro es obligatorio para crear o actualizar pacientes");
        }
        
     }
    
    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> actualizar(
            @PathVariable int id,
            @Valid @RequestBody PacienteRequestDto requestDto) {

        Paciente existente = pacienteUseCase.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        Paciente actualizado = pacienteUseCase.actualizar(id, mapper.toDomain(requestDto));
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        PacienteResponseDto responseDto = mapper.toResponseDto(actualizado);
        return ResponseEntity.ok(responseDto);
    }

    
    //actualizar estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<PacienteResponseDto> actualizarEstado(
            @PathVariable int id,
            @RequestParam(required = false) Boolean estado,
            @RequestBody(required = false) EstadoPatchRequest request) {

        Boolean estadoFinal = estado != null ? estado : (request != null ? request.getEstado() : null);
        if (estadoFinal == null) {
            throw new IllegalArgumentException("El campo estado es obligatorio");
        }

        Paciente actualizado = pacienteUseCase.actualizarEstado(id, estadoFinal);
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