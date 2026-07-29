package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.CatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICatalogoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private ICatalogoService servicioCatalogo;
	
	@GetMapping
    public String leerPagina(Model model) {
    List<CatalogoResponseDto> catalogos = servicioCatalogo.listarCatalogos().stream()
        .sorted(Comparator.comparing(CatalogoResponseDto::getIdCatalogo,
            Comparator.nullsLast(Comparator.naturalOrder())).reversed())
        .toList();
    model.addAttribute("listacatalogos", catalogos);
        return "catalogos/listarcatalogo";
    }

    @GetMapping("/nuevo")
    public String nuevoCatalogo(Model model) {
        model.addAttribute("catalogo", new CatalogoRequestDto());
        configurarFormulario(model, false);
        return "catalogos/crearCatalogo";
    }

    @PostMapping("/guardar")
    public String guardarCatalogo(@Valid @ModelAttribute CatalogoRequestDto catalogo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("catalogo", catalogo);
            configurarFormulario(model, false);
            return "catalogos/crearCatalogo";
        }
        try {
            servicioCatalogo.guardarCatalogo(catalogo);
            redirectAttributes.addFlashAttribute("success", "Catálogo registrado correctamente");
            return "redirect:/catalogo";
        } catch (RuntimeException e) {
            String mensaje = e.getMessage() == null || e.getMessage().isBlank()
                    ? "No se pudo registrar el catálogo. Verifique que no exista previamente."
                    : e.getMessage();
            model.addAttribute("error", mensaje);
            model.addAttribute("catalogo", catalogo);
            configurarFormulario(model, false);
            return "catalogos/crearCatalogo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarCatalogo(@PathVariable Integer id, Model model) {
        CatalogoResponseDto catalogoActual = servicioCatalogo.buscarPorId(id);
        CatalogoRequestDto catalogo = new CatalogoRequestDto();
        catalogo.setIdCatalogo(catalogoActual.getIdCatalogo());
        catalogo.setDescripcion(catalogoActual.getDescripcion());
        catalogo.setEstado(catalogoActual.isEstado());
        model.addAttribute("catalogo", catalogo);
        configurarFormulario(model, true);
        return "catalogos/crearCatalogo";
    }

    @PostMapping("/actualizar")
    public String actualizarCatalogo(@Valid @ModelAttribute CatalogoRequestDto catalogo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("catalogo", catalogo);
            configurarFormulario(model, true);
            return "catalogos/crearCatalogo";
        }
        try {
            servicioCatalogo.actualizarCatalogo(catalogo.getIdCatalogo(), catalogo);
            redirectAttributes.addFlashAttribute("success", "Catálogo actualizado correctamente");
            return "redirect:/catalogo";
        } catch (RuntimeException e) {
            String mensaje = e.getMessage() == null || e.getMessage().isBlank()
                    ? "No se pudo actualizar el catálogo. Verifique que no exista previamente."
                    : e.getMessage();
            model.addAttribute("error", mensaje);
            model.addAttribute("catalogo", catalogo);
            configurarFormulario(model, true);
            return "catalogos/crearCatalogo";
        }
    }

    private void configurarFormulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
    }
}
