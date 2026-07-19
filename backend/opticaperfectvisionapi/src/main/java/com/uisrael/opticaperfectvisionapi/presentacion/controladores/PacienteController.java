package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.PacienteRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.PacienteResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IPacienteDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    private final IPacienteUseCase pacienteUseCase;
    private final IPacienteDtoMapper mapper;

    public PacienteController(IPacienteUseCase pacienteUseCase, IPacienteDtoMapper mapper) {
        this.pacienteUseCase = pacienteUseCase;
        this.mapper = mapper;
    }

    // Listar todos
    @GetMapping ("/all")
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
   /* @PostMapping
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
    */
    
    //1907
    @PostMapping
 
    public ResponseEntity<PacienteResponseDto> guardar(@Valid @RequestBody PacienteRequestDto requestDto) {
        if (requestDto.getFechaRegistro() == null) {
            requestDto.setFechaRegistro(LocalDateTime.now());
        }
        if (requestDto.getIdUsuarioRegistro() == null) {
            requestDto.setIdUsuarioRegistro(1); // o el ID del usuario autenticado
        }

        Paciente guardado = pacienteUseCase.guardar(mapper.toDomain(requestDto));
        PacienteResponseDto responseDto = mapper.toResponseDto(guardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

//hasta aqui

    
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
    
//1807
 // Buscar por cédula
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<PacienteResponseDto> buscarPorCedula(@PathVariable String cedula) {
        return pacienteUseCase.findByCedula(cedula)
                .map(mapper::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Buscar por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<PacienteResponseDto> buscarPorCorreo(@PathVariable String correo) {
        return pacienteUseCase.findByCorreo(correo)
                .map(mapper::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por estado activo
    @GetMapping("/activo/{activo}")
    public ResponseEntity<List<PacienteResponseDto>> findByActivo(@PathVariable Boolean activo) {
        return ResponseEntity.ok(
            pacienteUseCase.findByActivo(activo).stream()
                .map(mapper::toResponseDto).toList()
        );
    }

    // Buscar por nombre o apellido parcial
    @GetMapping("/nombre/{texto}")
    public ResponseEntity<List<PacienteResponseDto>> buscarPorNombreOApellido(@PathVariable String texto) {
        return ResponseEntity.ok(
            pacienteUseCase.buscarPorNombreOApellido(texto).stream()
                .map(mapper::toResponseDto).toList()
        );
    }

    // Listar todos ordenados por fecha de registro
    @GetMapping("/ordenados")
    public ResponseEntity<List<PacienteResponseDto>> listarTodosOrdenados() {
        return ResponseEntity.ok(
            pacienteUseCase.listarTodosOrdenados().stream()
                .map(mapper::toResponseDto).toList()
        );
    }

    

}