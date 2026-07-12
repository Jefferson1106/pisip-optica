package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IPacienteRepositorio;

public class PacienteUseCaseImpl implements IPacienteUseCase {

    private final IPacienteRepositorio repositorio;

    public PacienteUseCaseImpl(IPacienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Paciente guardar(Paciente nuevoPaciente) {
        return repositorio.guardar(nuevoPaciente);
    }

    @Override
    public Paciente buscarPorId(int idPaciente) {
        return repositorio.buscarPorId(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Override
    public List<Paciente> listarTodos() {
        return repositorio.listarTodos();
    }

     @Override
   public Paciente actualizar(int idPaciente, Paciente pacienteActualizado) {
        repositorio.buscarPorId(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Paciente actualizado = new Paciente(
                pacienteActualizado.getCedula(),
                pacienteActualizado.getUsuarioAdministrador(),
                pacienteActualizado.getNombres(),
                pacienteActualizado.getApellidos(),
                pacienteActualizado.getDireccion(),
                pacienteActualizado.getTelefono(),
                pacienteActualizado.getCorreo(),
                pacienteActualizado.getFechaNacimiento(),
                pacienteActualizado.getFechaRegistro(),
                pacienteActualizado.getActivo()
        );

        return repositorio.guardar(actualizado);
    }

    @Override
    public void eliminar(String cedula) {
        repositorio.eliminar(cedula);
    }


}
