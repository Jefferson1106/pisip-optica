package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICertificadoPdfService;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleExamen;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

@Controller
@RequestMapping("/certificados")
public class CertificadoController {

    private final IExamenVisualService examenVisualService;
    private final IPacienteService pacienteService;
    private final ICertificadoPdfService certificadoPdfService;
    private final IDetalleExamen detalleExamenService;

    public CertificadoController(IExamenVisualService examenVisualService, IPacienteService pacienteService,
            ICertificadoPdfService certificadoPdfService,
            IDetalleExamen detalleExamenService) {
        this.examenVisualService = examenVisualService;
        this.pacienteService = pacienteService;
        this.certificadoPdfService = certificadoPdfService;
        this.detalleExamenService = detalleExamenService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) Integer idPaciente,
            @RequestParam(required = false) String cedula,
            Model model) {
        try {
            List<PacienteResponseDto> pacientesTodos = pacienteService.listarPacientes();
            List<PacienteResponseDto> pacientes = pacientesTodos.stream()
                    .filter(paciente -> Boolean.TRUE.equals(paciente.getActivo()))
                    .toList();

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

            List<ExamenVisualResponseDto> examenes;
            if (!cedulaFiltro.isBlank() && idPacienteFiltrado == null) {
                examenes = List.of();
            } else {
                examenes = idPacienteFiltrado != null
                        ? examenVisualService.listarExamenesPorPaciente(idPacienteFiltrado)
                        : examenVisualService.listarExamenesVisuales();
            }

            examenes = examenes.stream()
                .sorted(Comparator.comparing(ExamenVisualResponseDto::getIdExamen,
                    Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();

            Map<Integer, String> cedulasPorPaciente = pacientesTodos.stream()
                    .filter(paciente -> paciente.getIdPaciente() != null)
                    .collect(Collectors.toMap(
                            PacienteResponseDto::getIdPaciente,
                            paciente -> paciente.getCedula() != null ? paciente.getCedula() : "",
                            (actual, reemplazo) -> actual));
            Map<Integer, String> nombresPorPaciente = pacientesTodos.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                    PacienteResponseDto::getIdPaciente,
                    paciente -> paciente.getNombres() != null ? paciente.getNombres() : "",
                    (actual, reemplazo) -> actual));
            Map<Integer, String> apellidosPorPaciente = pacientesTodos.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                    PacienteResponseDto::getIdPaciente,
                    paciente -> paciente.getApellidos() != null ? paciente.getApellidos() : "",
                    (actual, reemplazo) -> actual));

            model.addAttribute("listapacientes", pacientes);
            model.addAttribute("listacertificados", examenes);
            model.addAttribute("idPacienteSeleccionado", idPacienteFiltrado);
            model.addAttribute("cedulaFiltro", cedulaFiltro);
            model.addAttribute("cedulasPorPaciente", cedulasPorPaciente);
            model.addAttribute("nombresPorPaciente", nombresPorPaciente);
            model.addAttribute("apellidosPorPaciente", apellidosPorPaciente);
        } catch (RuntimeException ex) {
            model.addAttribute("listapacientes", List.of());
            model.addAttribute("listacertificados", List.of());
            model.addAttribute("idPacienteSeleccionado", null);
            model.addAttribute("cedulaFiltro", "");
            model.addAttribute("cedulasPorPaciente", Map.of());
            model.addAttribute("nombresPorPaciente", Map.of());
            model.addAttribute("apellidosPorPaciente", Map.of());
            model.addAttribute("error", "No fue posible cargar certificados en este momento.");
        }
        return "/certificados/listarcertificado";
    }

    @GetMapping("/ver/{idExamen}")
    public String verCertificado(@PathVariable Integer idExamen, Model model, RedirectAttributes redirectAttributes) {
        try {
            ExamenVisualResponseDto examen = examenVisualService.buscarPorId(idExamen);
            DetalleExamenResponseDto detalleExamen = obtenerDetalleExamen(idExamen);
            model.addAttribute("certificado", examen);
            model.addAttribute("detalleExamen", detalleExamen);
            return "/certificados/verCertificado";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/certificados";
        }
    }

    @GetMapping("/pdf/{idExamen}")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Integer idExamen) {
        ExamenVisualResponseDto examen = examenVisualService.buscarPorId(idExamen);
        DetalleExamenResponseDto detalleExamen = obtenerDetalleExamen(idExamen);
        byte[] pdf = certificadoPdfService.generarCertificadoPdf(examen, detalleExamen);

        String filename = "certificado-CERT-" + examen.getIdExamen() + ".pdf";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

            private DetalleExamenResponseDto obtenerDetalleExamen(Integer idExamen) {
            return detalleExamenService.listarDetalleExamen().stream()
                .filter(detalle -> detalle.getExamenVisual() != null
                    && detalle.getExamenVisual().getIdExamen() != null
                    && idExamen.equals(detalle.getExamenVisual().getIdExamen()))
                .sorted(Comparator.comparing(DetalleExamenResponseDto::getIdDetExamen,
                    Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .findFirst()
                .orElse(null);
            }
}
