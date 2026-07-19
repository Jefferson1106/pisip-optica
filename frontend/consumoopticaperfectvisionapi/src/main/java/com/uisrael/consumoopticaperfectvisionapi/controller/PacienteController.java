package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        return "paciente/listarPaciente";
    }

    @GetMapping("/nuevo")
    public String crearPaciente(Model model) {
        model.addAttribute("paciente", new PacienteRequestDto());
        return "paciente/crearPaciente";
    }


    //funcioanal

    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute PacienteRequestDto paciente,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        try {
            servicioPaciente.guardarPaciente(paciente);
            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("success", "Paciente registrado correctamente");
            return "redirect:/paciente";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("paciente", paciente);
            return "paciente/crearPaciente";
        }
    }
    
    //funcional validar
    
 // Mostrar formulario de edición con datos cargados
    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        // Llamas al servicio para obtener el paciente por ID
        PacienteResponseDto paciente = servicioPaciente.buscarPorId(id);
        
        // Lo agregas al modelo con el nombre "paciente"
        model.addAttribute("paciente", paciente);
        
        // Retornas la vista de edición
        return "paciente/actualizarPaciente";
    }


    // Guardar cambios del paciente
    @PostMapping("/actualizar")
    public String actualizarPaciente(@ModelAttribute PacienteRequestDto paciente,
                                     RedirectAttributes redirectAttributes,
                                     Model model) {
        try {
            servicioPaciente.actualizarPaciente(paciente);
            redirectAttributes.addFlashAttribute("success", "Datos actualizados exitosamente");
            return "redirect:/paciente";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("paciente", paciente);
            return "paciente/actualizarPaciente";
        }
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            servicioPaciente.eliminarPaciente(id);
            redirectAttributes.addFlashAttribute("success", "Paciente eliminado (borrado lógico)");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/paciente";
    }


}
