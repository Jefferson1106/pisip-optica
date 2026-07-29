package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleCatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.CatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICatalogoService;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/detalle-catalogo")
public class DetalleCatalogoController {

    private final IDetalleCatalogoService servicioDetalleCatalogo;
    private final ICatalogoService servicioCatalogo;

    public DetalleCatalogoController(IDetalleCatalogoService servicioDetalleCatalogo,
            ICatalogoService servicioCatalogo) {
        this.servicioDetalleCatalogo = servicioDetalleCatalogo;
        this.servicioCatalogo = servicioCatalogo;
    }

    @GetMapping
    public String listar(Model model) {
        List<DetalleCatalogoResponseDto> detallesCatalogo = servicioDetalleCatalogo.listarDetalleCatalogos().stream()
            .sorted(Comparator.comparing(DetalleCatalogoResponseDto::getIdDetalleCatalogo,
                Comparator.nullsLast(Comparator.naturalOrder())).reversed())
            .toList();
        model.addAttribute("listadetallecatalogos", detallesCatalogo);
        return "detallecatalogo/listardetallecatalogo";
    }

    @GetMapping("/nuevo")
    public String nuevoDetalleCatalogo(Model model) {
        model.addAttribute("detalleCatalogo", new DetalleCatalogoRequestDto());
        cargarCatalogos(model);
        configurarFormulario(model, false);
        return "detallecatalogo/crearDetalleCatalogo";
    }

    @PostMapping("/guardar")
    public String guardarDetalleCatalogo(@Valid @ModelAttribute DetalleCatalogoRequestDto detalleCatalogo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("detalleCatalogo", detalleCatalogo);
            cargarCatalogos(model);
            configurarFormulario(model, false);
            return "detallecatalogo/crearDetalleCatalogo";
        }
        try {
            servicioDetalleCatalogo.guardarDetalleCatalogo(detalleCatalogo);
            redirectAttributes.addFlashAttribute("success", "Detalle de catálogo registrado correctamente");
            return "redirect:/detalle-catalogo";
        } catch (RuntimeException e) {
            model.addAttribute("error", obtenerMensajeError(e,
                    "No se pudo registrar el detalle de catálogo. Verifique que el nombre no esté registrado."));
            model.addAttribute("detalleCatalogo", detalleCatalogo);
            cargarCatalogos(model);
            configurarFormulario(model, false);
            return "detallecatalogo/crearDetalleCatalogo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarDetalleCatalogo(@PathVariable Integer id, Model model) {
        DetalleCatalogoResponseDto detalleActual = servicioDetalleCatalogo.buscarPorId(id);
        DetalleCatalogoRequestDto detalleCatalogo = new DetalleCatalogoRequestDto();
        detalleCatalogo.setIdDetalleCatalogo(detalleActual.getIdDetalleCatalogo());
        detalleCatalogo.setIdCatalogo(detalleActual.getIdCatalogo());
        detalleCatalogo.setNombre(detalleActual.getNombre());
        detalleCatalogo.setIdentificador(detalleActual.getIdentificador());
        detalleCatalogo.setEstado(detalleActual.isEstado());
        model.addAttribute("detalleCatalogo", detalleCatalogo);
        cargarCatalogos(model);
        configurarFormulario(model, true);
        return "detallecatalogo/crearDetalleCatalogo";
    }

    @PostMapping("/actualizar")
    public String actualizarDetalleCatalogo(@Valid @ModelAttribute DetalleCatalogoRequestDto detalleCatalogo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("detalleCatalogo", detalleCatalogo);
            cargarCatalogos(model);
            configurarFormulario(model, true);
            return "detallecatalogo/crearDetalleCatalogo";
        }
        try {
            servicioDetalleCatalogo.actualizarDetalleCatalogo(detalleCatalogo.getIdDetalleCatalogo(), detalleCatalogo);
            redirectAttributes.addFlashAttribute("success", "Detalle de catálogo actualizado correctamente");
            return "redirect:/detalle-catalogo";
        } catch (RuntimeException e) {
            model.addAttribute("error", obtenerMensajeError(e,
                    "No se pudo actualizar el detalle de catálogo. Verifique que el nombre no esté registrado."));
            model.addAttribute("detalleCatalogo", detalleCatalogo);
            cargarCatalogos(model);
            configurarFormulario(model, true);
            return "detallecatalogo/crearDetalleCatalogo";
        }
    }

    private void cargarCatalogos(Model model) {
        List<CatalogoResponseDto> listacatalogos = servicioCatalogo.listarCatalogos();
        model.addAttribute("listacatalogos", listacatalogos);
    }

    private void configurarFormulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
    }

    private String obtenerMensajeError(RuntimeException error, String mensajePredeterminado) {
        return error.getMessage() == null || error.getMessage().isBlank()
                ? mensajePredeterminado
                : error.getMessage();
    }
}
