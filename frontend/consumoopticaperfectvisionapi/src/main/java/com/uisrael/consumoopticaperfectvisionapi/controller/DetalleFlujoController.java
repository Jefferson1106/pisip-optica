package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleExamenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleEntregaRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.DetalleOrdenRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleExamen;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleEntrega;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleOrden;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenEntregaService;
import com.uisrael.consumoopticaperfectvisionapi.services.impl.ProductoService;

@Controller
public class DetalleFlujoController {

    private final IDetalleExamen servicioDetalleExamen;
    private final IDetalleOrden servicioDetalleOrden;
    private final IDetalleEntrega servicioDetalleEntrega;
    private final IOrdenEntregaService servicioOrdenEntrega;
    private final ProductoService servicioProducto;

    public DetalleFlujoController(IDetalleExamen servicioDetalleExamen,
            IDetalleOrden servicioDetalleOrden,
            IDetalleEntrega servicioDetalleEntrega,
            IOrdenEntregaService servicioOrdenEntrega,
            ProductoService servicioProducto) {
        this.servicioDetalleExamen = servicioDetalleExamen;
        this.servicioDetalleOrden = servicioDetalleOrden;
        this.servicioDetalleEntrega = servicioDetalleEntrega;
        this.servicioOrdenEntrega = servicioOrdenEntrega;
        this.servicioProducto = servicioProducto;
    }

    @GetMapping("/examenes-visuales/{idExamen}/detalles")
    public String verDetallesExamen(@PathVariable Integer idExamen, Model model) {
        List<DetalleExamenResponseDto> detalles = servicioDetalleExamen.listarDetalleExamen().stream()
                .filter(detalle -> detalle.getExamenVisual() != null
                        && detalle.getExamenVisual().getIdExamen() != null
                        && detalle.getExamenVisual().getIdExamen().equals(idExamen))
            .sorted(Comparator.comparing(DetalleExamenResponseDto::getIdDetExamen,
                Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();

        model.addAttribute("idExamen", idExamen);
        model.addAttribute("listadetalles", detalles);
        return "examenesvisuales/listarDetalleExamen";
    }

    @GetMapping("/examenes-visuales/{idExamen}/detalles/nuevo")
    public String nuevoDetalleExamen(@PathVariable Integer idExamen, Model model) {
        DetalleExamenRequestDto detalle = new DetalleExamenRequestDto();
        DetalleExamenRequestDto.ExamenVisualRef examenRef = new DetalleExamenRequestDto.ExamenVisualRef();
        examenRef.setIdExamen(idExamen);
        detalle.setExamenVisual(examenRef);
        model.addAttribute("idExamen", idExamen);
        model.addAttribute("idDetExamen", null);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("detalleExamen", detalle);
        return "examenesvisuales/formDetalleExamen";
    }

    @PostMapping("/examenes-visuales/{idExamen}/detalles/guardar")
    public String guardarDetalleExamen(@PathVariable Integer idExamen,
            @Valid @ModelAttribute("detalleExamen") DetalleExamenRequestDto detalleExamen,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos marcados en el formulario");
            model.addAttribute("idExamen", idExamen);
            model.addAttribute("idDetExamen", null);
            model.addAttribute("modoEdicion", false);
            return "examenesvisuales/formDetalleExamen";
        }
        try {
            prepararExamenVisualRef(detalleExamen, idExamen);
            servicioDetalleExamen.guardarDetalleExamen(detalleExamen);
            redirectAttributes.addFlashAttribute("success", "Detalle de examen registrado correctamente");
            return "redirect:/examenes-visuales/{idExamen}/detalles";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("idExamen", idExamen);
            model.addAttribute("idDetExamen", null);
            model.addAttribute("modoEdicion", false);
            return "examenesvisuales/formDetalleExamen";
        }
    }

    @GetMapping("/examenes-visuales/{idExamen}/detalles/editar/{idDetExamen}")
    public String editarDetalleExamen(@PathVariable Integer idExamen,
            @PathVariable Integer idDetExamen,
            Model model) {
        DetalleExamenResponseDto detalleActual = servicioDetalleExamen.buscarDetalleExamenPorId(idDetExamen);
        DetalleExamenRequestDto form = mapearDetalleExamenARequest(detalleActual, idExamen);
        model.addAttribute("idExamen", idExamen);
        model.addAttribute("idDetExamen", idDetExamen);
        model.addAttribute("modoEdicion", true);
        model.addAttribute("detalleExamen", form);
        return "examenesvisuales/formDetalleExamen";
    }

    @PostMapping("/examenes-visuales/{idExamen}/detalles/actualizar/{idDetExamen}")
    public String actualizarDetalleExamen(@PathVariable Integer idExamen,
            @PathVariable Integer idDetExamen,
            @Valid @ModelAttribute("detalleExamen") DetalleExamenRequestDto detalleExamen,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Revise los campos marcados en el formulario");
            model.addAttribute("idExamen", idExamen);
            model.addAttribute("idDetExamen", idDetExamen);
            model.addAttribute("modoEdicion", true);
            return "examenesvisuales/formDetalleExamen";
        }
        try {
            prepararExamenVisualRef(detalleExamen, idExamen);
            servicioDetalleExamen.actualizarDetalleExamen(idDetExamen, detalleExamen);
            redirectAttributes.addFlashAttribute("success", "Detalle de examen actualizado correctamente");
            return "redirect:/examenes-visuales/{idExamen}/detalles";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("idExamen", idExamen);
            model.addAttribute("idDetExamen", idDetExamen);
            model.addAttribute("modoEdicion", true);
            return "examenesvisuales/formDetalleExamen";
        }
    }

    @GetMapping("/examenes-visuales/{idExamen}/detalles/eliminar/{idDetExamen}")
    public String eliminarDetalleExamen(@PathVariable Integer idExamen,
            @PathVariable Integer idDetExamen,
            RedirectAttributes redirectAttributes) {
        try {
            servicioDetalleExamen.eliminarDetalleExamen(idDetExamen);
            redirectAttributes.addFlashAttribute("success", "Detalle de examen eliminado correctamente");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/examenes-visuales/{idExamen}/detalles";
    }

    @GetMapping("/ordenespedido/{idPedido}/detalles")
    public String verDetallesPedido(@PathVariable Integer idPedido, Model model) {
        List<DetalleOrdenResponseDto> detalles = servicioDetalleOrden.listarDetalleOrden().stream()
                .filter(detalle -> detalle.getIdPedido() != null && detalle.getIdPedido().equals(idPedido))
            .sorted(Comparator.comparing(DetalleOrdenResponseDto::getIdDetOrden,
                Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();

        BigDecimal totalGeneral = detalles.stream()
				.map(DetalleOrdenResponseDto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("idPedido", idPedido);
        model.addAttribute("listadetalles", detalles);
        model.addAttribute("totalGeneral", totalGeneral);
        return "ordenespedido/listarDetallePedido";
    }

    @GetMapping("/ordenespedido/{idPedido}/detalles/nuevo")
    public String nuevoDetallePedido(@PathVariable Integer idPedido, Model model) {
        DetalleOrdenRequestDto detalle = new DetalleOrdenRequestDto();
        detalle.setIdPedido(idPedido);
        detalle.setFechaRegistro(LocalDateTime.now());
        model.addAttribute("idPedido", idPedido);
        model.addAttribute("idDetOrden", null);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("detalleOrden", detalle);
        cargarProductosPedido(model);
        return "ordenespedido/formDetallePedido";
    }

    @PostMapping("/ordenespedido/{idPedido}/detalles/guardar")
    public String guardarDetallePedido(@PathVariable Integer idPedido,
            @ModelAttribute("detalleOrden") DetalleOrdenRequestDto detalleOrden,
            RedirectAttributes redirectAttributes,
            Model model) {
        try {
            detalleOrden.setIdPedido(idPedido);
            if (detalleOrden.getFechaRegistro() == null) {
                detalleOrden.setFechaRegistro(LocalDateTime.now());
            }
            servicioDetalleOrden.guardarDetalleOrden(detalleOrden);
            redirectAttributes.addFlashAttribute("success", "Detalle de pedido registrado correctamente");
            return "redirect:/ordenespedido/{idPedido}/detalles";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("idPedido", idPedido);
            model.addAttribute("idDetOrden", null);
            model.addAttribute("modoEdicion", false);
            cargarProductosPedido(model);
            return "ordenespedido/formDetallePedido";
        }
    }

    @GetMapping("/ordenespedido/{idPedido}/detalles/editar/{idDetOrden}")
    public String editarDetallePedido(@PathVariable Integer idPedido,
            @PathVariable Integer idDetOrden,
            Model model) {
        DetalleOrdenResponseDto detalleActual = servicioDetalleOrden.buscarDetalleOrdenPorId(idDetOrden);
        DetalleOrdenRequestDto form = mapearDetalleOrdenARequest(detalleActual, idPedido);
        model.addAttribute("idPedido", idPedido);
        model.addAttribute("idDetOrden", idDetOrden);
        model.addAttribute("modoEdicion", true);
        model.addAttribute("detalleOrden", form);
        cargarProductosPedido(model);
        return "ordenespedido/formDetallePedido";
    }

    @PostMapping("/ordenespedido/{idPedido}/detalles/actualizar/{idDetOrden}")
    public String actualizarDetallePedido(@PathVariable Integer idPedido,
            @PathVariable Integer idDetOrden,
            @ModelAttribute("detalleOrden") DetalleOrdenRequestDto detalleOrden,
            RedirectAttributes redirectAttributes,
            Model model) {
        try {
            detalleOrden.setIdPedido(idPedido);
            servicioDetalleOrden.actualizarDetalleOrden(idDetOrden, detalleOrden);
            redirectAttributes.addFlashAttribute("success", "Detalle de pedido actualizado correctamente");
            return "redirect:/ordenespedido/{idPedido}/detalles";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("idPedido", idPedido);
            model.addAttribute("idDetOrden", idDetOrden);
            model.addAttribute("modoEdicion", true);
            cargarProductosPedido(model);
            return "ordenespedido/formDetallePedido";
        }
    }

    @GetMapping("/ordenespedido/{idPedido}/detalles/eliminar/{idDetOrden}")
    public String eliminarDetallePedido(@PathVariable Integer idPedido,
            @PathVariable Integer idDetOrden,
            RedirectAttributes redirectAttributes) {
        try {
            servicioDetalleOrden.eliminarDetalleOrden(idDetOrden);
            redirectAttributes.addFlashAttribute("success", "Detalle de pedido eliminado correctamente");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/ordenespedido/{idPedido}/detalles";
    }

    @GetMapping("/ordenes-entrega/{idEntrega}/detalles")
    public String verDetallesEntrega(@PathVariable Integer idEntrega, Model model) {
        List<DetalleEntregaResponseDto> detalles = servicioDetalleEntrega.listarDetalleEntrega().stream()
                .filter(detalle -> detalle.getIdEntrega() != null && detalle.getIdEntrega().equals(idEntrega))
                .sorted(Comparator.comparing(DetalleEntregaResponseDto::getIdDetEntrega,
                        Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();

        OrdenEntregaResponseDto ordenEntrega = servicioOrdenEntrega.buscarPorId(idEntrega);
        boolean entregaRecibida = ordenEstaRecibida(ordenEntrega);
        Map<Integer, String> productosPorId = construirMapaProductosPedidoPro(ordenEntrega.getIdPedido());

        model.addAttribute("idEntrega", idEntrega);
        model.addAttribute("idPedido", ordenEntrega.getIdPedido());
        model.addAttribute("listadetalles", detalles);
        model.addAttribute("productosPorId", productosPorId);
        model.addAttribute("entregaRecibida", entregaRecibida);
        return "ordenEntrega/listarDetalleEntrega";
    }

    @GetMapping("/ordenes-entrega/{idEntrega}/detalles/nuevo")
    public String nuevoDetalleEntrega(@PathVariable Integer idEntrega, Model model,
            RedirectAttributes redirectAttributes) {
        OrdenEntregaResponseDto ordenEntrega = servicioOrdenEntrega.buscarPorId(idEntrega);
        if (ordenEstaRecibida(ordenEntrega)) {
            redirectAttributes.addFlashAttribute("error", "No se puede agregar detalles: la entrega ya fue confirmada como recibida.");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        }

        DetalleEntregaRequestDto detalle = new DetalleEntregaRequestDto();
        detalle.setIdEntrega(idEntrega);
        detalle.setEstado(Boolean.TRUE);
        detalle.setFechaRegistro(LocalDateTime.now());

        model.addAttribute("idEntrega", idEntrega);
        model.addAttribute("idPedido", ordenEntrega.getIdPedido());
        model.addAttribute("idDetEntrega", null);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("detalleEntrega", detalle);
        cargarProductosEntrega(model, ordenEntrega.getIdPedido(), null);
        return "ordenEntrega/formDetalleEntrega";
    }

    @PostMapping("/ordenes-entrega/{idEntrega}/detalles/guardar")
    public String guardarDetalleEntrega(@PathVariable Integer idEntrega,
            @ModelAttribute("detalleEntrega") DetalleEntregaRequestDto detalleEntrega,
            RedirectAttributes redirectAttributes,
            Model model) {
        OrdenEntregaResponseDto ordenEntrega = servicioOrdenEntrega.buscarPorId(idEntrega);
        if (ordenEstaRecibida(ordenEntrega)) {
            redirectAttributes.addFlashAttribute("error", "No se puede guardar el detalle: la entrega ya fue confirmada como recibida.");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        }

        try {
            detalleEntrega.setIdEntrega(idEntrega);
            if (detalleEntrega.getFechaRegistro() == null) {
                detalleEntrega.setFechaRegistro(LocalDateTime.now());
            }
            servicioDetalleEntrega.guardarDetalleEntrega(detalleEntrega);
            redirectAttributes.addFlashAttribute("success", "Detalle de entrega registrado correctamente");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("idEntrega", idEntrega);
            model.addAttribute("idPedido", ordenEntrega.getIdPedido());
            model.addAttribute("idDetEntrega", null);
            model.addAttribute("modoEdicion", false);
            cargarProductosEntrega(model, ordenEntrega.getIdPedido(), detalleEntrega.getIdProducto());
            return "ordenEntrega/formDetalleEntrega";
        }
    }

    @GetMapping("/ordenes-entrega/{idEntrega}/detalles/editar/{idDetEntrega}")
    public String editarDetalleEntrega(@PathVariable Integer idEntrega,
            @PathVariable Integer idDetEntrega,
            Model model,
            RedirectAttributes redirectAttributes) {
        OrdenEntregaResponseDto ordenEntrega = servicioOrdenEntrega.buscarPorId(idEntrega);
        if (ordenEstaRecibida(ordenEntrega)) {
            redirectAttributes.addFlashAttribute("error", "No se puede editar detalles: la entrega ya fue confirmada como recibida.");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        }

        DetalleEntregaResponseDto detalleActual = servicioDetalleEntrega.buscarDetalleEntregaPorId(idDetEntrega);
        DetalleEntregaRequestDto form = mapearDetalleEntregaARequest(detalleActual, idEntrega);

        model.addAttribute("idEntrega", idEntrega);
        model.addAttribute("idPedido", ordenEntrega.getIdPedido());
        model.addAttribute("idDetEntrega", idDetEntrega);
        model.addAttribute("modoEdicion", true);
        model.addAttribute("detalleEntrega", form);
        cargarProductosEntrega(model, ordenEntrega.getIdPedido(), form.getIdProducto());
        return "ordenEntrega/formDetalleEntrega";
    }

    @PostMapping("/ordenes-entrega/{idEntrega}/detalles/actualizar/{idDetEntrega}")
    public String actualizarDetalleEntrega(@PathVariable Integer idEntrega,
            @PathVariable Integer idDetEntrega,
            @ModelAttribute("detalleEntrega") DetalleEntregaRequestDto detalleEntrega,
            RedirectAttributes redirectAttributes,
            Model model) {
        OrdenEntregaResponseDto ordenEntrega = servicioOrdenEntrega.buscarPorId(idEntrega);
        if (ordenEstaRecibida(ordenEntrega)) {
            redirectAttributes.addFlashAttribute("error", "No se puede actualizar el detalle: la entrega ya fue confirmada como recibida.");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        }

        try {
            detalleEntrega.setIdEntrega(idEntrega);
            if (detalleEntrega.getFechaRegistro() == null) {
                detalleEntrega.setFechaRegistro(LocalDateTime.now());
            }
            servicioDetalleEntrega.actualizarDetalleEntrega(idDetEntrega, detalleEntrega);
            redirectAttributes.addFlashAttribute("success", "Detalle de entrega actualizado correctamente");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("idEntrega", idEntrega);
            model.addAttribute("idPedido", ordenEntrega.getIdPedido());
            model.addAttribute("idDetEntrega", idDetEntrega);
            model.addAttribute("modoEdicion", true);
            cargarProductosEntrega(model, ordenEntrega.getIdPedido(), detalleEntrega.getIdProducto());
            return "ordenEntrega/formDetalleEntrega";
        }
    }

    @GetMapping("/ordenes-entrega/{idEntrega}/detalles/eliminar/{idDetEntrega}")
    public String eliminarDetalleEntrega(@PathVariable Integer idEntrega,
            @PathVariable Integer idDetEntrega,
            RedirectAttributes redirectAttributes) {
        OrdenEntregaResponseDto ordenEntrega = servicioOrdenEntrega.buscarPorId(idEntrega);
        if (ordenEstaRecibida(ordenEntrega)) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el detalle: la entrega ya fue confirmada como recibida.");
            return "redirect:/ordenes-entrega/{idEntrega}/detalles";
        }

        try {
            servicioDetalleEntrega.eliminarDetalleEntrega(idDetEntrega);
            redirectAttributes.addFlashAttribute("success", "Detalle de entrega eliminado correctamente");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/ordenes-entrega/{idEntrega}/detalles";
    }

    private boolean ordenEstaRecibida(OrdenEntregaResponseDto ordenEntrega) {
        return ordenEntrega != null && ordenEntrega.getRecibido() != null && ordenEntrega.getRecibido();
    }

    private void prepararExamenVisualRef(DetalleExamenRequestDto detalleExamen, Integer idExamen) {
        if (detalleExamen.getExamenVisual() == null) {
            detalleExamen.setExamenVisual(new DetalleExamenRequestDto.ExamenVisualRef());
        }
        detalleExamen.getExamenVisual().setIdExamen(idExamen);
    }

    private DetalleExamenRequestDto mapearDetalleExamenARequest(DetalleExamenResponseDto detalleActual, Integer idExamen) {
        DetalleExamenRequestDto form = new DetalleExamenRequestDto();
        prepararExamenVisualRef(form, idExamen);
        form.setEsferaDistanciaOd(detalleActual.getEsferaDistanciaOd());
        form.setCilindroDistanciaOd(detalleActual.getCilindroDistanciaOd());
        form.setEjeDistanciaOd(detalleActual.getEjeDistanciaOd());
        form.setEsferaDistanciaOi(detalleActual.getEsferaDistanciaOi());
        form.setCilindroDistanciaOi(detalleActual.getCilindroDistanciaOi());
        form.setEjeDistanciaOi(detalleActual.getEjeDistanciaOi());
        form.setAdicionOd(detalleActual.getAdicionOd());
        form.setAdicionOi(detalleActual.getAdicionOi());
        form.setDistanciaPupilar(detalleActual.getDistanciaPupilar());
        form.setAlturaBifocal(detalleActual.getAlturaBifocal());
        form.setAlturaProgresivo(detalleActual.getAlturaProgresivo());
        form.setEsferaLecturaOd(detalleActual.getEsferaLecturaOd());
        form.setCilindroLecturaOd(detalleActual.getCilindroLecturaOd());
        form.setEjeLecturaOd(detalleActual.getEjeLecturaOd());
        form.setEsferaLecturaOi(detalleActual.getEsferaLecturaOi());
        form.setCilindroLecturaOi(detalleActual.getCilindroLecturaOi());
        form.setEjeLecturaOi(detalleActual.getEjeLecturaOi());
        return form;
    }

    private DetalleOrdenRequestDto mapearDetalleOrdenARequest(DetalleOrdenResponseDto detalleActual, Integer idPedido) {
        DetalleOrdenRequestDto form = new DetalleOrdenRequestDto();
        form.setIdPedido(idPedido);
        form.setIdProducto(detalleActual.getIdProducto());
        form.setTratamiento(detalleActual.getTratamiento());
        form.setCantidad(detalleActual.getCantidad());
        form.setPrecioUnitario(detalleActual.getPrecioUnitario());
        form.setFechaRegistro(detalleActual.getFechaRegistro());
        return form;
    }

    private void cargarProductosPedido(Model model) {
        model.addAttribute("listaproductos", servicioProducto.listar().stream()
                .filter(producto -> producto.isEstado())
                .sorted(Comparator.comparing(producto -> producto.getNombre().toLowerCase()))
                .toList());
    }

        private void cargarProductosEntrega(Model model, Integer idPedido, Integer idProductoSeleccionado) {
        List<DetalleOrdenResponseDto> detallesPedido = servicioDetalleOrden.listarDetalleOrden().stream()
            .filter(item -> item.getIdPedido() != null && item.getIdPedido().equals(idPedido))
            .sorted(Comparator.comparing(DetalleOrdenResponseDto::getIdDetOrden,
                Comparator.nullsLast(Comparator.naturalOrder())).reversed())
            .toList();

        List<ProductoEntregaOption> productos = detallesPedido.stream()
            .map(item -> new ProductoEntregaOption(
                item.getIdDetOrden(),
                (item.getProductoNombre() != null ? item.getProductoNombre() : "Detalle anterior")
                    + " | Cant: " + (item.getCantidad() != null ? item.getCantidad() : 0)
                    + " | Detalle #" + item.getIdDetOrden()))
            .toList();

        model.addAttribute("listaproductosEntrega", productos);
        model.addAttribute("idProductoSeleccionado", idProductoSeleccionado);
        }

        private Map<Integer, String> construirMapaProductosPedidoPro(Integer idPedido) {
        return servicioDetalleOrden.listarDetalleOrden().stream()
            .filter(item -> item.getIdPedido() != null && item.getIdPedido().equals(idPedido))
            .collect(Collectors.toMap(
                DetalleOrdenResponseDto::getIdDetOrden,
                item -> (item.getProductoNombre() != null ? item.getProductoNombre() : "Detalle anterior")
                    + " | Detalle #" + item.getIdDetOrden(),
                (actual, reemplazo) -> actual));
    }

    private DetalleEntregaRequestDto mapearDetalleEntregaARequest(DetalleEntregaResponseDto detalleActual, Integer idEntrega) {
        DetalleEntregaRequestDto form = new DetalleEntregaRequestDto();
        form.setIdEntrega(idEntrega);
        form.setIdProducto(detalleActual.getIdProducto());
        form.setCantidad(detalleActual.getCantidad());
        form.setEstado(detalleActual.getEstado());
        form.setFechaRegistro(detalleActual.getFechaRegistro());
        return form;
    }

    public static class ProductoEntregaOption {
        private final Integer idDetOrden;
        private final String descripcion;

        public ProductoEntregaOption(Integer idDetOrden, String descripcion) {
            this.idDetOrden = idDetOrden;
            this.descripcion = descripcion;
        }

        public Integer getIdDetOrden() {
            return idDetOrden;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
