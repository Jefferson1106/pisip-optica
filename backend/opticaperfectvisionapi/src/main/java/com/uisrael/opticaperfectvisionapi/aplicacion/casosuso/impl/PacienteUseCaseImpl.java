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

    //15
    @Override
    public Paciente actualizar(int idPaciente, Paciente pacienteActualizado) {
        Paciente existente = repositorio.buscarPorId(idPaciente)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // No actualizar la cédula si ya existe
        // existente.setCedula(pacienteActualizado.getCedula());

        existente.setUsuarioAdministrador(pacienteActualizado.getUsuarioAdministrador());
        existente.setNombres(pacienteActualizado.getNombres());
        existente.setApellidos(pacienteActualizado.getApellidos());
        existente.setDireccion(pacienteActualizado.getDireccion());
        existente.setTelefono(pacienteActualizado.getTelefono());
        existente.setCorreo(pacienteActualizado.getCorreo());
        existente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        existente.setFechaRegistro(pacienteActualizado.getFechaRegistro());
        existente.setActivo(pacienteActualizado.getActivo());

        return repositorio.guardar(existente);
    }


    @Override
    public void eliminar(String cedula) {
        repositorio.eliminar(cedula);
    }

}
