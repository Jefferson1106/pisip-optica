package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.PacienteRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;


@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService servicioPaciente;

    @GetMapping
    public String listarPacientes(Model model) {
        List<PacienteResponseDto> resultadoBD = servicioPaciente.listarPacientes();
        model.addAttribute("listapacientes", resultadoBD);
        return "/paciente/listarPaciente";
    }

    @GetMapping("/nuevo")
    public String crearPaciente(Model model) {
        model.addAttribute("paciente", new PacienteRequestDto());
        return "/paciente/crearPaciente";
    }

    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute PacienteRequestDto paciente) {
        servicioPaciente.guardarPaciente(paciente);
        return "redirect:/paciente";
    }
}
