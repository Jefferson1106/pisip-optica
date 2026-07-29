package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.PacienteRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.LoginResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService servicioPaciente;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String listarPacientes(Model model) {
        try {
            List<PacienteResponseDto> resultadoBD = servicioPaciente.listarPacientes().stream()
                .sorted(Comparator
                    .comparing(PacienteResponseDto::getFechaRegistro,
                        Comparator.nullsLast(Comparator.reverseOrder()))
                    .thenComparing(PacienteResponseDto::getIdPaciente,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
            model.addAttribute("listapacientes", resultadoBD);
        } catch (RuntimeException ex) {
            model.addAttribute("listapacientes", List.of());
            model.addAttribute("error", "No fue posible cargar pacientes en este momento.");
        }
        return "paciente/listarPaciente";
    }

    @GetMapping("/nuevo")
    public String crearPaciente(Model model) {
        model.addAttribute("paciente", new PacienteRequestDto());
        configurarFormulario(model, false);
        return "paciente/crearPaciente";
    }


    //funcioanal

    @PostMapping("/guardar")
    public String guardarPaciente(@Valid @ModelAttribute PacienteRequestDto paciente,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Model model,
                                  HttpSession session) {
        if (bindingResult.hasErrors()) {
            String mensajeValidacion = bindingResult.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .filter(msg -> msg != null && !msg.isBlank())
                .findFirst()
                .orElse("Revise los campos obligatorios del formulario");
            model.addAttribute("error", mensajeValidacion);
            model.addAttribute("paciente", paciente);
            configurarFormulario(model, false);
            return "paciente/crearPaciente";
        }
        try {
            String cedula = paciente.getCedula() != null ? paciente.getCedula().trim() : "";
            if (!esCedulaEcuatorianaValida(cedula)) {
                throw new IllegalArgumentException("La cédula ingresada no es válida");
            }
            paciente.setCedula(cedula);
            completarAuditoriaPaciente(paciente, session);
            servicioPaciente.guardarPaciente(paciente);
            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("success", "Paciente registrado correctamente");
            return "redirect:/paciente";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("paciente", paciente);
            configurarFormulario(model, false);
            return "paciente/crearPaciente";
        }
    }
    
    //funcional validar
    
 // Mostrar formulario de edición con datos cargados
    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        // Llamas al servicio para obtener el paciente por ID
        PacienteResponseDto paciente = servicioPaciente.buscarPorId(id);
        PacienteRequestDto pacienteForm = new PacienteRequestDto();
        pacienteForm.setIdPaciente(paciente.getIdPaciente() != null ? paciente.getIdPaciente().longValue() : null);
        pacienteForm.setCedula(paciente.getCedula());
        pacienteForm.setNombres(paciente.getNombres());
        pacienteForm.setApellidos(paciente.getApellidos());
        pacienteForm.setDireccion(paciente.getDireccion());
        pacienteForm.setTelefono(paciente.getTelefono());
        pacienteForm.setCorreo(paciente.getCorreo());
        pacienteForm.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteForm.setFechaRegistro(paciente.getFechaRegistro());
        pacienteForm.setIdUsuarioRegistro(paciente.getIdUsuarioRegistro());
        pacienteForm.setActivo(paciente.getActivo());
        
        // Lo agregas al modelo con el nombre "paciente"
        model.addAttribute("paciente", pacienteForm);
        configurarFormulario(model, true);
        
        // Retornas la vista de edición
        return "paciente/crearPaciente";
    }


    // Guardar cambios del paciente
    @PostMapping("/actualizar")
    public String actualizarPaciente(@Valid @ModelAttribute PacienteRequestDto paciente,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     Model model,
                                     HttpSession session) {
        if (bindingResult.hasErrors()) {
            String mensajeValidacion = bindingResult.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .filter(msg -> msg != null && !msg.isBlank())
                .findFirst()
                .orElse("Revise los campos obligatorios del formulario");
            model.addAttribute("error", mensajeValidacion);
            model.addAttribute("paciente", paciente);
            configurarFormulario(model, true);
            return "paciente/crearPaciente";
        }
        try {
            String cedula = paciente.getCedula() != null ? paciente.getCedula().trim() : "";
            if (!esCedulaEcuatorianaValida(cedula)) {
                throw new IllegalArgumentException("La cédula ingresada no es válida");
            }
            paciente.setCedula(cedula);
            conservarAuditoriaPaciente(paciente);
            servicioPaciente.actualizarPaciente(paciente);
            redirectAttributes.addFlashAttribute("success", "Datos actualizados exitosamente");
            return "redirect:/paciente";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("paciente", paciente);
            configurarFormulario(model, true);
            return "paciente/crearPaciente";
        }
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            servicioPaciente.eliminarPaciente(id);
            redirectAttributes.addFlashAttribute("success", "Paciente eliminado exitosamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/paciente";
    }

    private void configurarFormulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
    }

    private void completarAuditoriaPaciente(PacienteRequestDto paciente, HttpSession session) {
        if (paciente.getFechaRegistro() == null) {
            paciente.setFechaRegistro(LocalDateTime.now());
        }

        if (paciente.getIdUsuarioRegistro() != null) {
            return;
        }

        Object usuarioSesion = session.getAttribute(AuthController.SESSION_USER);
        if (usuarioSesion instanceof LoginResponseDto usuario && usuario.getIdUsuario() != null) {
            paciente.setIdUsuarioRegistro(usuario.getIdUsuario());
        }
    }

    private void conservarAuditoriaPaciente(PacienteRequestDto paciente) {
        PacienteResponseDto pacienteActual = servicioPaciente.buscarPorId(paciente.getIdPaciente());
        paciente.setFechaRegistro(pacienteActual.getFechaRegistro());
        paciente.setIdUsuarioRegistro(pacienteActual.getIdUsuarioRegistro());
    }

    private boolean esCedulaEcuatorianaValida(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false;
        }

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito >= 6) {
            return false;
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }

        int verificadorEsperado = (10 - (suma % 10)) % 10;
        int verificadorReal = Character.getNumericValue(cedula.charAt(9));
        return verificadorEsperado == verificadorReal;
    }


}
