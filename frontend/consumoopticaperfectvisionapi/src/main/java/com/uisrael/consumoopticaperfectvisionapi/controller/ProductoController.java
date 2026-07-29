package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.Comparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.ProductoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ProductoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.impl.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	private final ProductoService servicio;
	private final ProveedorService proveedorServicio;
	public ProductoController(ProductoService servicio, ProveedorService proveedorServicio) {
		this.servicio = servicio; this.proveedorServicio = proveedorServicio;
	}
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("productos", servicio.listar().stream()
				.sorted(Comparator.comparing(ProductoResponseDto::getIdProducto).reversed()).toList());
		return "productos/listar";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		ProductoRequestDto dto = new ProductoRequestDto(); dto.setEstado(true);
		return formulario(model, dto, false);
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		ProductoResponseDto actual = servicio.buscar(id);
		ProductoRequestDto dto = new ProductoRequestDto();
		dto.setIdProducto(actual.getIdProducto()); dto.setCodigo(actual.getCodigo()); dto.setNombre(actual.getNombre());
		dto.setDescripcion(actual.getDescripcion()); dto.setPrecio(actual.getPrecio());
		dto.setIdProveedor(actual.getIdProveedor()); dto.setEstado(actual.isEstado());
		return formulario(model, dto, true);
	}
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("producto") ProductoRequestDto dto, BindingResult result,
			Model model, RedirectAttributes flash) {
		boolean editar = dto.getIdProducto() != null;
		if (result.hasErrors()) return formulario(model, dto, editar);
		try {
			if (editar) servicio.actualizar(dto.getIdProducto(), dto); else servicio.guardar(dto);
			flash.addFlashAttribute("success", editar ? "Producto actualizado correctamente" : "Producto registrado correctamente");
			return "redirect:/productos";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return formulario(model, dto, editar);
		}
	}
	private String formulario(Model model, ProductoRequestDto dto, boolean editar) {
		model.addAttribute("producto", dto); model.addAttribute("modoEdicion", editar);
		model.addAttribute("proveedores", proveedorServicio.listar().stream().filter(p -> p.isEstado()).toList());
		return "productos/formulario";
	}
}
