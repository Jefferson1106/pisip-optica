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

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleCatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.CatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICatalogoService;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;

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
        model.addAttribute("listadetallecatalogos", servicioDetalleCatalogo.listarDetalleCatalogos());
        return "detallecatalogo/listardetallecatalogo";
    }

    @GetMapping("/nuevo")
    public String nuevoDetalleCatalogo(Model model) {
        model.addAttribute("detalleCatalogo", new DetalleCatalogoRequestDto());
        cargarCatalogos(model);
        return "detallecatalogo/crearDetalleCatalogo";
    }

    @PostMapping("/guardar")
    public String guardarDetalleCatalogo(@ModelAttribute DetalleCatalogoRequestDto detalleCatalogo,
            RedirectAttributes redirectAttributes, Model model) {
        try {
            servicioDetalleCatalogo.guardarDetalleCatalogo(detalleCatalogo);
            redirectAttributes.addFlashAttribute("success", "Detalle de catálogo registrado correctamente");
            return "redirect:/detalle-catalogo";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("detalleCatalogo", detalleCatalogo);
            cargarCatalogos(model);
            return "detallecatalogo/crearDetalleCatalogo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarDetalleCatalogo(@PathVariable Integer id, Model model) {
        DetalleCatalogoResponseDto detalleCatalogo = servicioDetalleCatalogo.buscarPorId(id);
        model.addAttribute("detalleCatalogo", detalleCatalogo);
        cargarCatalogos(model);
        return "detallecatalogo/actualizarDetalleCatalogo";
    }

    @PostMapping("/actualizar")
    public String actualizarDetalleCatalogo(@ModelAttribute DetalleCatalogoResponseDto detalleCatalogo,
            RedirectAttributes redirectAttributes, Model model) {
        try {
            DetalleCatalogoRequestDto requestDto = new DetalleCatalogoRequestDto();
            requestDto.setIdCatalogo(detalleCatalogo.getIdCatalogo());
            requestDto.setNombre(detalleCatalogo.getNombre());
            requestDto.setIdentificador(detalleCatalogo.getIdentificador());
            requestDto.setEstado(detalleCatalogo.isEstado());
            servicioDetalleCatalogo.actualizarDetalleCatalogo(detalleCatalogo.getIdDetalleCatalogo(), requestDto);
            redirectAttributes.addFlashAttribute("success", "Detalle de catálogo actualizado correctamente");
            return "redirect:/detalle-catalogo";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("detalleCatalogo", detalleCatalogo);
            cargarCatalogos(model);
            return "detallecatalogo/actualizarDetalleCatalogo";
        }
    }

    private void cargarCatalogos(Model model) {
        List<CatalogoResponseDto> listacatalogos = servicioCatalogo.listarCatalogos();
        model.addAttribute("listacatalogos", listacatalogos);
    }
}
