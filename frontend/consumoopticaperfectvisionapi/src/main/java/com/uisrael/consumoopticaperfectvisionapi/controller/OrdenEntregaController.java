package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenPedidoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenEntregaService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ordenes-entrega")
public class OrdenEntregaController {

    @Autowired
    private IOrdenEntregaService servicioOrden;

    @Autowired
    private IOrdenPedido servicioOrdenPedido;

    @Autowired
    private IDetalleCatalogoService servicioDetalleCatalogo;

    @Autowired
    private IPacienteService servicioPaciente;

    // Listar todas las órdenes
    @GetMapping
    public String listarOrdenes(Model model) {
        try {
            List<OrdenPedidoResponseDto> pedidos = servicioOrdenPedido.listarOrdenPedido();
            Map<Integer, OrdenPedidoResponseDto> pedidosPorId = pedidos.stream()
                    .filter(pedido -> pedido.getIdPedido() != null)
                    .collect(Collectors.toMap(OrdenPedidoResponseDto::getIdPedido, pedido -> pedido, (a, b) -> a));

            List<PacienteResponseDto> pacientes = servicioPaciente.listarPacientes();

            Map<Integer, String> cedulasPorPaciente = pacientes.stream()
                    .filter(paciente -> paciente.getIdPaciente() != null)
                    .collect(Collectors.toMap(
                            PacienteResponseDto::getIdPaciente,
                            paciente -> paciente.getCedula() != null ? paciente.getCedula() : "",
                            (a, b) -> a));
            Map<Integer, String> nombresPorPaciente = pacientes.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                    PacienteResponseDto::getIdPaciente,
                    paciente -> paciente.getNombres() != null ? paciente.getNombres() : "",
                    (a, b) -> a));
            Map<Integer, String> apellidosPorPaciente = pacientes.stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                    PacienteResponseDto::getIdPaciente,
                    paciente -> paciente.getApellidos() != null ? paciente.getApellidos() : "",
                    (a, b) -> a));

            List<OrdenEntregaResponseDto> resultadoBD = servicioOrden.listarOrdenes();
            model.addAttribute("listaOrdenes", resultadoBD);
            model.addAttribute("pedidosPorId", pedidosPorId);
            model.addAttribute("cedulasPorPaciente", cedulasPorPaciente);
            model.addAttribute("nombresPorPaciente", nombresPorPaciente);
            model.addAttribute("apellidosPorPaciente", apellidosPorPaciente);
        } catch (RuntimeException ex) {
            model.addAttribute("listaOrdenes", List.of());
            model.addAttribute("pedidosPorId", Map.of());
            model.addAttribute("cedulasPorPaciente", Map.of());
            model.addAttribute("nombresPorPaciente", Map.of());
            model.addAttribute("apellidosPorPaciente", Map.of());
            model.addAttribute("error", "No fue posible cargar ordenes de entrega en este momento.");
        }
        return "ordenEntrega/listarOrdenes";

    }

    // Mostrar formulario de nueva orden
    @GetMapping("/nuevo")
    public String crearOrden(@RequestParam(required = false) Integer idPedido, Model model) {
        OrdenEntregaRequestDto orden = new OrdenEntregaRequestDto();
        orden.setFechaRegistro(LocalDateTime.now());
        orden.setFechaEntrega(java.time.LocalDate.now());
        orden.setRecibido(Boolean.FALSE);
        if (idPedido != null) {
            OrdenPedidoResponseDto pedido = servicioOrdenPedido.buscarPorId(idPedido.longValue());
            orden.setIdPedido(idPedido);
            if (pedido.getFechaEntrega() != null) {
                orden.setFechaEntrega(pedido.getFechaEntrega());
            }
            model.addAttribute("pedidoPreseleccionado", true);
        } else {
            model.addAttribute("pedidoPreseleccionado", false);
        }
        cargarPedidos(model, orden.getIdPedido(), false);
        model.addAttribute("ordenEntrega", orden);
        configurarFormulario(model, false);
        return "ordenEntrega/crearOrden";
    }

    // Guardar nueva orden
    @PostMapping("/guardar")
    public String guardarOrden(@Valid @ModelAttribute OrdenEntregaRequestDto orden,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("ordenEntrega", orden);
            model.addAttribute("pedidoPreseleccionado", orden.getIdPedido() != null);
            cargarPedidos(model, orden.getIdPedido(), false);
            configurarFormulario(model, false);
            return "ordenEntrega/crearOrden";
        }
        try {
            if (orden.getFechaRegistro() == null) {
                orden.setFechaRegistro(LocalDateTime.now());
            }
            servicioOrden.guardarOrden(orden);
            marcarPedidoComoTerminado(orden.getIdPedido());
            redirectAttributes.addFlashAttribute("success",
                    "Orden de entrega registrada y pedido marcado como TERMINADO");
            return "redirect:/ordenes-entrega";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ordenEntrega", orden);
            model.addAttribute("pedidoPreseleccionado", orden.getIdPedido() != null);
            cargarPedidos(model, orden.getIdPedido(), false);
            configurarFormulario(model, false);
            return "ordenEntrega/crearOrden";
        }
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarOrden(@PathVariable Integer id, Model model) {
        OrdenEntregaResponseDto ordenActual = servicioOrden.buscarPorId(id);
        OrdenEntregaRequestDto orden = convertirARequest(ordenActual);
        orden.setIdEntrega(ordenActual.getIdEntrega());
        model.addAttribute("ordenEntrega", orden); // ResponseDto con idEntrega
        model.addAttribute("pedidoPreseleccionado", false);
        cargarPedidos(model, orden.getIdPedido(), true);
        configurarFormulario(model, true);
        return "ordenEntrega/crearOrden";
    }

    // Guardar cambios de la orden
    @PostMapping("/actualizar")
    public String actualizarOrden(@Valid @ModelAttribute OrdenEntregaRequestDto orden,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos obligatorios del formulario");
            model.addAttribute("ordenEntrega", orden);
            model.addAttribute("pedidoPreseleccionado", false);
            cargarPedidos(model, orden.getIdPedido(), true);
            configurarFormulario(model, true);
            return "ordenEntrega/crearOrden";
        }
        try {
            orden.setFechaRegistro(LocalDateTime.now());
            servicioOrden.actualizarOrden(orden.getIdEntrega(), orden);
            redirectAttributes.addFlashAttribute("success", "Datos actualizados exitosamente");
            return "redirect:/ordenes-entrega";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ordenEntrega", orden);
            model.addAttribute("pedidoPreseleccionado", false);
            cargarPedidos(model, orden.getIdPedido(), true);
            configurarFormulario(model, true);
            return "ordenEntrega/crearOrden";
        }
    }

    @GetMapping("/recibir/{id}")
    public String marcarRecibida(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            OrdenEntregaResponseDto orden = servicioOrden.buscarPorId(id);
            OrdenEntregaRequestDto request = convertirARequest(orden);
            request.setRecibido(Boolean.TRUE);
            request.setFechaRegistro(LocalDateTime.now());
            servicioOrden.actualizarOrden(id, request);
            redirectAttributes.addFlashAttribute("success", "Orden marcada como recibida");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/ordenes-entrega";
    }

    // Eliminar orden (borrado lógico)
    @GetMapping("/eliminar/{id}")
    public String eliminarOrden(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            servicioOrden.eliminarOrden(id);
            redirectAttributes.addFlashAttribute("success", "Orden eliminada exitosamente");
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

    private void configurarFormulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
    }

    private void cargarPedidos(Model model, Integer idPedidoSeleccionado, boolean modoEdicion) {
        List<OrdenPedidoResponseDto> pedidos = servicioOrdenPedido.listarOrdenPedido().stream()
                .filter(pedido -> (pedido.getNombreEstadoPedido() != null
                                && "EN PROCESO".equalsIgnoreCase(pedido.getNombreEstadoPedido().trim()))
                        || (modoEdicion && idPedidoSeleccionado != null
                                && idPedidoSeleccionado.equals(pedido.getIdPedido())))
                .sorted(Comparator.comparing(OrdenPedidoResponseDto::getIdPedido,
                        Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();

        Map<Integer, String> cedulasPorPaciente = servicioPaciente.listarPacientes().stream()
                .filter(paciente -> paciente.getIdPaciente() != null)
                .collect(Collectors.toMap(
                        PacienteResponseDto::getIdPaciente,
                        paciente -> valor(paciente.getCedula()),
                        (actual, reemplazo) -> actual));

        List<PedidoOption> opciones = pedidos.stream()
                .map(pedido -> new PedidoOption(
                        pedido.getIdPedido(),
                    pedido.getFechaEntrega() != null ? pedido.getFechaEntrega().toString() : "",
                        "Orden de Pedido: #" + pedido.getIdPedido()
                                + " - " + valor(pedido.getPacienteNombre())
                                + " - Cédula: " + cedulasPorPaciente.getOrDefault(
                                        pedido.getIdPaciente(), "N/A")
                                + " - " + valor(pedido.getExamenDescripcion())
                                + " - " + valor(pedido.getFechaPedido())))
                .collect(Collectors.toList());

        model.addAttribute("listaPedidosEntrega", opciones);
        model.addAttribute("idPedidoSeleccionado", idPedidoSeleccionado);
    }

    private void marcarPedidoComoTerminado(Integer idPedido) {
        if (idPedido == null) {
            throw new IllegalArgumentException("Debe seleccionar una orden de pedido");
        }
        OrdenPedidoResponseDto pedido = servicioOrdenPedido.buscarPorId(idPedido.longValue());
        Integer idEstadoTerminado = obtenerIdEstadoTerminado();
        if (idEstadoTerminado.equals(pedido.getIdEstadoPedido())) {
            return;
        }

        OrdenPedidoRequestDto request = new OrdenPedidoRequestDto();
        request.setIdExamen(pedido.getIdExamen());
        request.setIdPaciente(pedido.getIdPaciente());
        request.setFechaPedido(pedido.getFechaPedido());
        request.setFechaEntrega(pedido.getFechaEntrega());
        request.setFechaRegistro(pedido.getFechaRegistro());
        request.setIdEstadoPedido(idEstadoTerminado);
        servicioOrdenPedido.actualizarOdenPedido(pedido.getIdPedido().longValue(), request);
    }

    private Integer obtenerIdEstadoTerminado() {
        return servicioDetalleCatalogo.listarDetalleCatalogos().stream()
                .filter(DetalleCatalogoResponseDto::isEstado)
                .filter(detalle -> detalle.getIdentificador() != null
                        && "EST".equalsIgnoreCase(detalle.getIdentificador().trim()))
                .filter(detalle -> detalle.getNombre() != null
                        && "TERMINADO".equalsIgnoreCase(detalle.getNombre().trim()))
                .map(DetalleCatalogoResponseDto::getIdDetalleCatalogo)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No existe un estado activo llamado TERMINADO para las órdenes de pedido"));
    }

    private String valor(Object valor) {
        return valor != null ? valor.toString() : "N/A";
    }

    public static class PedidoOption {
        private final Integer idPedido;
        private final String fechaEntrega;
        private final String descripcion;

        public PedidoOption(Integer idPedido, String fechaEntrega, String descripcion) {
            this.idPedido = idPedido;
            this.fechaEntrega = fechaEntrega;
            this.descripcion = descripcion;
        }

        public Integer getIdPedido() {
            return idPedido;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getFechaEntrega() {
            return fechaEntrega;
        }
    }
}
