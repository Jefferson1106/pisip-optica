package com.uisrael.consumoopticaperfectvisionapi.services;

import java.util.List;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.PacienteRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;

public interface IPacienteService {
    List<PacienteResponseDto> listarPacientes();
    void guardarPaciente(PacienteRequestDto nuevo);
}
