package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;

public interface IPacienteUseCase {
    Paciente guardar(Paciente nuevoPaciente);
    Paciente buscarPorId(int idPaciente);
    List<Paciente> listarTodos();
    Paciente actualizar(int idPaciente, Paciente pacienteActualizado);
    Paciente actualizarEstado(int idPaciente, Boolean estado);

    //1807
    Optional<Paciente> findByCedula(String cedula);
    Optional<Paciente> findByCorreo(String correo);
    List<Paciente> findByActivo(Boolean activo);
    List<Paciente> buscarPorNombreOApellido(String texto);
    List<Paciente> listarTodosOrdenados();
}
