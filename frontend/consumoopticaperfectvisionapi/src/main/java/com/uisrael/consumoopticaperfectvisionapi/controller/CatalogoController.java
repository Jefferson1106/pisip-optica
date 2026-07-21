package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CatalogoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.CatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICatalogoService;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private ICatalogoService servicioCatalogo;
	
	@GetMapping
    public String leerPagina(Model model) {
        model.addAttribute("listacatalogos", servicioCatalogo.listarCatalogos());
        return "catalogos/listarcatalogo";
    }

    @GetMapping("/nuevo")
    public String nuevoCatalogo(Model model) {
        model.addAttribute("catalogo", new CatalogoRequestDto());
        return "catalogos/crearCatalogo";
    }

    @PostMapping("/guardar")
    public String guardarCatalogo(@ModelAttribute CatalogoRequestDto catalogo,
            RedirectAttributes redirectAttributes, Model model) {
        try {
            servicioCatalogo.guardarCatalogo(catalogo);
            redirectAttributes.addFlashAttribute("success", "Catálogo registrado correctamente");
            return "redirect:/catalogo";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("catalogo", catalogo);
            return "catalogos/crearCatalogo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarCatalogo(@PathVariable Integer id, Model model) {
        CatalogoResponseDto catalogo = servicioCatalogo.buscarPorId(id);
        model.addAttribute("catalogo", catalogo);
        return "catalogos/actualizarCatalogo";
    }

    @PostMapping("/actualizar")
    public String actualizarCatalogo(@ModelAttribute CatalogoResponseDto catalogo,
            RedirectAttributes redirectAttributes, Model model) {
        try {
            CatalogoRequestDto requestDto = new CatalogoRequestDto();
            requestDto.setDescripcion(catalogo.getDescripcion());
            requestDto.setEstado(catalogo.isEstado());
            servicioCatalogo.actualizarCatalogo(catalogo.getIdCatalogo(), requestDto);
            redirectAttributes.addFlashAttribute("success", "Catálogo actualizado correctamente");
            return "redirect:/catalogo";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("catalogo", catalogo);
            return "catalogos/actualizarCatalogo";
        }
    }
}
