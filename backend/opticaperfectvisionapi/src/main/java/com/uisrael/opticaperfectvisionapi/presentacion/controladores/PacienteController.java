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

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> actualizar(@PathVariable int id,
                                               @Valid @RequestBody PacienteRequestDto requestDto) {
        validarIdUsuarioRegistro(requestDto);
        Paciente actualizado = pacienteUseCase.actualizar(id, mapper.toDomain(requestDto));
        PacienteResponseDto responseDto = mapper.toResponseDto(actualizado);
        if (responseDto.getIdUsuarioRegistro() == null) {
            responseDto.setIdUsuarioRegistro(requestDto.getIdUsuarioRegistro());
        }
        return ResponseEntity.ok(responseDto);
    }

    // Eliminar paciente
    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminar(@PathVariable String cedula) {
        pacienteUseCase.eliminar(cedula);
        return ResponseEntity.noContent().build();
    }

    private void validarIdUsuarioRegistro(PacienteRequestDto requestDto) {
        if (requestDto.getIdUsuarioRegistro() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "idUsuarioRegistro es obligatorio para crear o actualizar pacientes");
        }
    }
}