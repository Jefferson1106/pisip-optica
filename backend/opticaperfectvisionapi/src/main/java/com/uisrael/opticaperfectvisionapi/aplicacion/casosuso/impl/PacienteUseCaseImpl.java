package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;
import java.util.Optional;

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
	    public Paciente actualizarEstado(int idPaciente, Boolean estado) {
	        Paciente existente = repositorio.buscarPorId(idPaciente)
	            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

	        existente.setActivo(estado);
	        return repositorio.guardar(existente);
	    }

	 //1807
	 
	 @Override
	    public Optional<Paciente> findByCedula(String cedula) { return repositorio.findByCedula(cedula); }

	    @Override
	    public Optional<Paciente> findByCorreo(String correo) { return repositorio.findByCorreo(correo); }

	    @Override
	    public List<Paciente> findByActivo(Boolean activo) { return repositorio.findByActivo(activo); }

	    @Override
	    public List<Paciente> buscarPorNombreOApellido(String texto) { return repositorio.buscarPorNombreOApellido(texto); }

	    @Override
	    public List<Paciente> listarTodosOrdenados() { return repositorio.listarTodosOrdenados(); }
	
}
