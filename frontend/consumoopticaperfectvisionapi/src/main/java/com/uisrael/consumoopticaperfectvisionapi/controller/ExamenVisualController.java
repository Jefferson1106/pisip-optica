package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ExamenVisualRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

import jakarta.validation.Valid;

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
        try {
            List<PacienteResponseDto> pacientes = servicioPaciente.listarPacientes();
            Map<Integer, String> cedulasPorPaciente = pacientes.stream()
                    .filter(paciente -> paciente.getIdPaciente() != null)
                    .collect(Collectors.toMap(
                            PacienteResponseDto::getIdPaciente,
                            paciente -> paciente.getCedula() != null ? paciente.getCedula() : "",
                            (actual, reemplazo) -> actual));
            Map<Integer, String> nombresPorPaciente = pacientes.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                    PacienteResponseDto::getIdPaciente,
                    paciente -> paciente.getNombres() != null ? paciente.getNombres() : "",
                    (actual, reemplazo) -> actual));
            Map<Integer, String> apellidosPorPaciente = pacientes.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                    PacienteResponseDto::getIdPaciente,
                    paciente -> paciente.getApellidos() != null ? paciente.getApellidos() : "",
                    (actual, reemplazo) -> actual));

            List<ExamenVisualResponseDto> examenes = servicioExamenVisual.listarExamenesVisuales();
            model.addAttribute("listaexamenesvisuales", examenes);
            model.addAttribute("cedulasPorPaciente", cedulasPorPaciente);
            model.addAttribute("nombresPorPaciente", nombresPorPaciente);
            model.addAttribute("apellidosPorPaciente", apellidosPorPaciente);
        } catch (RuntimeException ex) {
            model.addAttribute("listaexamenesvisuales", List.of());
            model.addAttribute("cedulasPorPaciente", Map.of());
            model.addAttribute("nombresPorPaciente", Map.of());
            model.addAttribute("apellidosPorPaciente", Map.of());
            model.addAttribute("error", "No fue posible cargar examenes visuales en este momento.");
        }
        return "examenesvisuales/listarexamenvisual";
    }

    @GetMapping("/historial")
    public String historial(@RequestParam(required = false) Integer idPaciente,
            @RequestParam(required = false) String cedula,
            Model model) {
        List<PacienteResponseDto> pacientesTodos = servicioPaciente.listarPacientes();
        String cedulaFiltro = cedula != null ? cedula.trim() : "";

        Integer idPacienteFiltrado = idPaciente;
        if (!cedulaFiltro.isBlank()) {
            idPacienteFiltrado = pacientesTodos.stream()
                    .filter(paciente -> paciente.getCedula() != null
                            && paciente.getCedula().trim().equalsIgnoreCase(cedulaFiltro))
                    .map(PacienteResponseDto::getIdPaciente)
                    .findFirst()
                    .orElse(null);
        }
        final Integer idPacienteFinal = idPacienteFiltrado;

        List<PacienteResponseDto> pacientes = pacientesTodos.stream()
                .filter(paciente -> Boolean.TRUE.equals(paciente.getActivo())
                || (idPacienteFinal != null && idPacienteFinal.equals(paciente.getIdPaciente())))
                .toList();

        Map<Integer, String> cedulasPorPaciente = pacientesTodos.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                        PacienteResponseDto::getIdPaciente,
                        paciente -> paciente.getCedula() != null ? paciente.getCedula() : "",
                        (actual, reemplazo) -> actual));

        List<ExamenVisualResponseDto> examenes;
        try {
            if (!cedulaFiltro.isBlank() && idPacienteFinal == null) {
                examenes = List.of();
            } else {
                examenes = idPacienteFinal != null
                ? servicioExamenVisual.listarExamenesPorPaciente(idPacienteFinal)
                : servicioExamenVisual.listarExamenesVisuales();
            }
        } catch (RuntimeException ex) {
            examenes = List.of();
            model.addAttribute("error", "No fue posible cargar el historial en este momento.");
        }

        model.addAttribute("listapacientes", pacientes);
        model.addAttribute("cedulasPorPaciente", cedulasPorPaciente);
        model.addAttribute("listaexamenes", examenes);
        model.addAttribute("idPacienteSeleccionado", idPacienteFinal);
        model.addAttribute("cedulaFiltro", cedulaFiltro);
        return "examenesvisuales/historialExamenes";
    }

    @GetMapping("/nuevo")
    public String nuevoExamenVisual(@RequestParam(required = false) Integer idPaciente, Model model) {
        ExamenVisualRequestDto examenVisual = new ExamenVisualRequestDto();
        examenVisual.setFechaExamen(LocalDate.now());
        if (idPaciente != null) {
            examenVisual.setIdPaciente(idPaciente);
        }
        model.addAttribute("examenVisual", examenVisual);
        cargarPacientesActivos(model);
        aplicarLimiteFechaExamen(model);
        configurarFormulario(model, false);
        return "examenesvisuales/crearExamenVisual";
    }

    @PostMapping("/guardar")
    public String guardarExamenVisual(@Valid @ModelAttribute ExamenVisualRequestDto examenVisual,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("examenVisual", examenVisual);
            cargarPacientesActivos(model);
            aplicarLimiteFechaExamen(model);
            configurarFormulario(model, false);
            return "examenesvisuales/crearExamenVisual";
        }
        try {
            servicioExamenVisual.guardarExamenVisual(examenVisual);
            redirectAttributes.addFlashAttribute("success", "Examen visual registrado correctamente");
            return "redirect:/examenes-visuales";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("examenVisual", examenVisual);
            cargarPacientesActivos(model);
            aplicarLimiteFechaExamen(model);
            configurarFormulario(model, false);
            return "examenesvisuales/crearExamenVisual";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarExamenVisual(@PathVariable Integer id, Model model) {
        ExamenVisualResponseDto examenActual = servicioExamenVisual.buscarPorId(id);
        ExamenVisualRequestDto examenVisual = new ExamenVisualRequestDto();
        examenVisual.setIdExamen(examenActual.getIdExamen());
        examenVisual.setIdPaciente(examenActual.getIdPaciente());
        examenVisual.setFechaExamen(examenActual.getFechaExamen());
        examenVisual.setObservaciones(examenActual.getObservaciones());
        examenVisual.setEstado(examenActual.isEstado());
        model.addAttribute("examenVisual", examenVisual);
        cargarPacientesActivos(model);
        aplicarLimiteFechaExamen(model);
        configurarFormulario(model, true);
        return "examenesvisuales/crearExamenVisual";
    }

    @PostMapping("/actualizar")
    public String actualizarExamenVisual(@Valid @ModelAttribute ExamenVisualRequestDto examenVisual,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("examenVisual", examenVisual);
            cargarPacientesActivos(model);
            aplicarLimiteFechaExamen(model);
            configurarFormulario(model, true);
            return "examenesvisuales/crearExamenVisual";
        }
        try {
            servicioExamenVisual.actualizarExamenVisual(examenVisual.getIdExamen(), examenVisual);
            redirectAttributes.addFlashAttribute("success", "Examen visual actualizado correctamente");
            return "redirect:/examenes-visuales";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("examenVisual", examenVisual);
            cargarPacientesActivos(model);
            aplicarLimiteFechaExamen(model);
            configurarFormulario(model, true);
            return "examenesvisuales/crearExamenVisual";
        }
    }

    private void cargarPacientesActivos(Model model) {
        List<PacienteResponseDto> pacientesActivos = servicioPaciente.listarPacientes().stream()
                .filter(paciente -> Boolean.TRUE.equals(paciente.getActivo()))
                .toList();
        model.addAttribute("listapacientes", pacientesActivos);
    }

    private void configurarFormulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
    }

    private void aplicarLimiteFechaExamen(Model model) {
        model.addAttribute("fechaMaximaExamen", LocalDate.now().toString());
    }
}
