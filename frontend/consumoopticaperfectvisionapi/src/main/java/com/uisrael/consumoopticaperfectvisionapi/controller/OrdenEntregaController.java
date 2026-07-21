package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import java.util.List;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenEntregaService;

@Controller
@RequestMapping("/ordenes-entrega")
public class OrdenEntregaController {

    @Autowired
    private IOrdenEntregaService servicioOrden;

    // Listar todas las órdenes
    @GetMapping
    public String listarOrdenes(Model model) {
        List<OrdenEntregaResponseDto> resultadoBD = servicioOrden.listarOrdenes();
        model.addAttribute("listaOrdenes", resultadoBD);
        return "ordenEntrega/listarOrdenes";
    }

    // Mostrar formulario de nueva orden
    @GetMapping("/nuevo")
    public String crearOrden(Model model) {
        model.addAttribute("ordenEntrega", new OrdenEntregaRequestDto());
        return "ordenEntrega/crearOrden";
    }

    // Guardar nueva orden
    @PostMapping("/guardar")
    public String guardarOrden(@ModelAttribute OrdenEntregaRequestDto orden,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        try {
            servicioOrden.guardarOrden(orden);
            redirectAttributes.addFlashAttribute("success", "Orden registrada correctamente");
            return "redirect:/ordenes-entrega";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ordenEntrega", orden);
            return "ordenEntrega/crearOrden";
        }
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarOrden(@PathVariable Integer id, Model model) {
        OrdenEntregaResponseDto orden = servicioOrden.buscarPorId(id);
        model.addAttribute("ordenEntrega", orden); // ResponseDto con idEntrega
        return "ordenEntrega/actualizarOrden";
    }

    // Guardar cambios de la orden
    @PostMapping("/actualizar")
    public String actualizarOrden(@ModelAttribute OrdenEntregaResponseDto orden,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        try {
            servicioOrden.actualizarOrden(orden.getIdEntrega(), convertirARequest(orden));
            redirectAttributes.addFlashAttribute("success", "Datos actualizados exitosamente");
            return "redirect:/ordenes-entrega";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ordenEntrega", orden);
            return "ordenEntrega/actualizarOrden";
        }
    }

    // Eliminar orden (borrado lógico)
    @GetMapping("/eliminar/{id}")
    public String eliminarOrden(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            servicioOrden.eliminarOrden(id);
            redirectAttributes.addFlashAttribute("success", "Orden eliminada (borrado lógico)");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/ordenes-entrega";
    }

    // Conversión ResponseDto -> RequestDto para actualizar
    private OrdenEntregaRequestDto convertirARequest(OrdenEntregaResponseDto response) {
        OrdenEntregaRequestDto request = new OrdenEntregaRequestDto();
        request.setIdPedido(response.getIdPedido());
        request.setFechaEntrega(response.getFechaEntrega());
        request.setRecibido(response.getRecibido());
        request.setObservaciones(response.getObservaciones());
        request.setFechaRegistro(response.getFechaRegistro());
        return request;
    }
}
