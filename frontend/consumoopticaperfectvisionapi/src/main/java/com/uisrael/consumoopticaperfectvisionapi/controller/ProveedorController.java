package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.Comparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ProveedorRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ProveedorResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.impl.ProveedorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
	private final ProveedorService servicio;
	public ProveedorController(ProveedorService servicio) { this.servicio = servicio; }

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("proveedores", servicio.listar().stream()
				.sorted(Comparator.comparing(ProveedorResponseDto::getIdProveedor).reversed()).toList());
		return "proveedores/listar";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		ProveedorRequestDto dto = new ProveedorRequestDto();
		dto.setEstado(true);
		model.addAttribute("proveedor", dto);
		model.addAttribute("modoEdicion", false);
		return "proveedores/formulario";
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		ProveedorResponseDto actual = servicio.buscar(id);
		ProveedorRequestDto dto = new ProveedorRequestDto();
		dto.setIdProveedor(actual.getIdProveedor()); dto.setNombre(actual.getNombre());
		dto.setIdentificacion(actual.getIdentificacion()); dto.setCorreo(actual.getCorreo());
		dto.setTelefono(actual.getTelefono()); dto.setDireccion(actual.getDireccion()); dto.setEstado(actual.isEstado());
		model.addAttribute("proveedor", dto); model.addAttribute("modoEdicion", true);
		return "proveedores/formulario";
	}
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("proveedor") ProveedorRequestDto dto, BindingResult result,
			Model model, RedirectAttributes flash) {
		boolean editar = dto.getIdProveedor() != null;
		if (result.hasErrors()) { model.addAttribute("modoEdicion", editar); return "proveedores/formulario"; }
		try {
			if (editar) servicio.actualizar(dto.getIdProveedor(), dto); else servicio.guardar(dto);
			flash.addFlashAttribute("success", editar ? "Proveedor actualizado correctamente" : "Proveedor registrado correctamente");
			return "redirect:/proveedores";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage()); model.addAttribute("modoEdicion", editar);
			return "proveedores/formulario";
		}
	}
}
