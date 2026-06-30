package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final IPacienteUseCase pacienteUseCase;

    public PacienteController(IPacienteUseCase pacienteUseCase) {
        this.pacienteUseCase = pacienteUseCase;
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteUseCase.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable int id) {
        Paciente paciente = pacienteUseCase.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    // Guardar nuevo paciente
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente nuevoPaciente) {
        Paciente guardado = pacienteUseCase.guardar(nuevoPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable int id,
                                               @RequestBody Paciente pacienteActualizado) {
        Paciente actualizado = pacienteUseCase.actualizar(id, pacienteActualizado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar paciente
    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminar(@PathVariable String cedula) {
        pacienteUseCase.eliminar(cedula);
        return ResponseEntity.noContent().build();
    }
}