package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ExamenVisualRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

@Controller
@RequestMapping({"/examenesvisuales", "/examenes-visuales"})
public class ExamenVisualController {

    private final IExamenVisualService servicioExamenVisual;
    private final IPacienteService servicioPaciente;

    public ExamenVisualController(IExamenVisualService servicioExamenVisual, IPacienteService servicioPaciente) {
        this.servicioExamenVisual = servicioExamenVisual;
        this.servicioPaciente = servicioPaciente;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaexamenesvisuales", servicioExamenVisual.listarExamenesVisuales());
        return "examenesvisuales/listarexamenvisual";
    }

    @GetMapping("/nuevo")
    public String nuevoExamenVisual(Model model) {
        model.addAttribute("examenVisual", new ExamenVisualRequestDto());
        cargarPacientesActivos(model);
        return "examenesvisuales/crearExamenVisual";
    }

    @PostMapping("/guardar")
    public String guardarExamenVisual(@ModelAttribute ExamenVisualRequestDto examenVisual,
            RedirectAttributes redirectAttributes, Model model) {
        try {
            servicioExamenVisual.guardarExamenVisual(examenVisual);
            redirectAttributes.addFlashAttribute("success", "Examen visual registrado correctamente");
            return "redirect:/examenes-visuales";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("examenVisual", examenVisual);
            cargarPacientesActivos(model);
            return "examenesvisuales/crearExamenVisual";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarExamenVisual(@PathVariable Integer id, Model model) {
        ExamenVisualResponseDto examenVisual = servicioExamenVisual.buscarPorId(id);
        model.addAttribute("examenVisual", examenVisual);
        cargarPacientesActivos(model);
        return "examenesvisuales/actualizarExamenVisual";
    }

    @PostMapping("/actualizar")
    public String actualizarExamenVisual(@ModelAttribute ExamenVisualResponseDto examenVisual,
            RedirectAttributes redirectAttributes, Model model) {
        try {
            ExamenVisualRequestDto requestDto = new ExamenVisualRequestDto();
            requestDto.setIdPaciente(examenVisual.getIdPaciente());
            requestDto.setFechaExamen(examenVisual.getFechaExamen());
            requestDto.setObservaciones(examenVisual.getObservaciones());
            requestDto.setEstado(examenVisual.isEstado());
            servicioExamenVisual.actualizarExamenVisual(examenVisual.getIdExamen(), requestDto);
            redirectAttributes.addFlashAttribute("success", "Examen visual actualizado correctamente");
            return "redirect:/examenes-visuales";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("examenVisual", examenVisual);
            cargarPacientesActivos(model);
            return "examenesvisuales/actualizarExamenVisual";
        }
    }

    private void cargarPacientesActivos(Model model) {
        List<PacienteResponseDto> pacientesActivos = servicioPaciente.listarPacientes().stream()
                .filter(paciente -> Boolean.TRUE.equals(paciente.getActivo()))
                .toList();
        model.addAttribute("listapacientes", pacientesActivos);
    }
}
