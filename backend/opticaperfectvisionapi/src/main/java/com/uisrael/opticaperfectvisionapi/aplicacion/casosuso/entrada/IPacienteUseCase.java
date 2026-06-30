package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada;

import java.util.List;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Paciente;

public interface IPacienteUseCase {
    Paciente guardar(Paciente nuevoPaciente);
    Paciente buscarPorId(int idPaciente);
    List<Paciente> listarTodos();
    Paciente actualizar(int idPaciente, Paciente pacienteActualizado);
    void eliminar(String cedula);
}
