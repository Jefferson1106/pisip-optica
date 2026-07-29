package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenPedidoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleOrden;
import com.uisrael.consumoopticaperfectvisionapi.services.ICertificadoPdfService;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenEntregaService;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ordenespedido")
public class OrdenPedidoController {
	
	@Autowired
	private IOrdenPedido servicioOrdenPedido;

	@Autowired
	private IPacienteService servicioPaciente;

	@Autowired
	private IExamenVisualService servicioExamenVisual;

	@Autowired
	private IDetalleCatalogoService servicioDetalleCatalogo;

	@Autowired
	private IOrdenEntregaService servicioOrdenEntrega;

	@Autowired
	private IDetalleOrden servicioDetalleOrden;

	@Autowired
	private ICertificadoPdfService servicioPdf;
	
	@GetMapping
	public String leerPagina(Model model) {
		try {
			List<PacienteResponseDto> pacientes = servicioPaciente.listarPacientes();
			var cedulasPorPaciente = pacientes.stream()
					.filter(paciente -> paciente.getIdPaciente() != null)
					.collect(java.util.stream.Collectors.toMap(
							PacienteResponseDto::getIdPaciente,
							paciente -> paciente.getCedula() != null ? paciente.getCedula() : "",
							(actual, reemplazo) -> actual));
			var nombresPorPaciente = pacientes.stream()
					.filter(paciente -> paciente.getIdPaciente() != null)
					.collect(java.util.stream.Collectors.toMap(
							PacienteResponseDto::getIdPaciente,
							paciente -> paciente.getNombres() != null ? paciente.getNombres() : "",
							(actual, reemplazo) -> actual));
			var apellidosPorPaciente = pacientes.stream()
					.filter(paciente -> paciente.getIdPaciente() != null)
					.collect(java.util.stream.Collectors.toMap(
							PacienteResponseDto::getIdPaciente,
							paciente -> paciente.getApellidos() != null ? paciente.getApellidos() : "",
							(actual, reemplazo) -> actual));

			List<OrdenPedidoResponseDto> resultadoBD = servicioOrdenPedido.listarOrdenPedido();
			var pedidosBloqueados = servicioOrdenEntrega.listarOrdenes().stream()
					.filter(entrega -> Boolean.TRUE.equals(entrega.getRecibido()))
					.map(entrega -> entrega.getIdPedido())
					.filter(java.util.Objects::nonNull)
					.collect(java.util.stream.Collectors.toSet());
			model.addAttribute("listaordenpedidos", resultadoBD);
			model.addAttribute("pedidosBloqueados", pedidosBloqueados);
			model.addAttribute("cedulasPorPaciente", cedulasPorPaciente);
			model.addAttribute("nombresPorPaciente", nombresPorPaciente);
			model.addAttribute("apellidosPorPaciente", apellidosPorPaciente);
		} catch (RuntimeException ex) {
			model.addAttribute("listaordenpedidos", List.of());
			model.addAttribute("cedulasPorPaciente", java.util.Map.of());
			model.addAttribute("nombresPorPaciente", java.util.Map.of());
			model.addAttribute("apellidosPorPaciente", java.util.Map.of());
			model.addAttribute("pedidosBloqueados", java.util.Set.of());
			model.addAttribute("error", "No fue posible cargar ordenes de pedido en este momento.");
		}
		return "ordenespedido/listarordenpedido";
	}

	@GetMapping("/pdf/{id}")
	public ResponseEntity<byte[]> descargarOrdenPedidoPdf(@PathVariable Integer id) {
		OrdenPedidoResponseDto pedido = servicioOrdenPedido.buscarPorId(id.longValue());
		PacienteResponseDto paciente = servicioPaciente.listarPacientes().stream()
				.filter(item -> Objects.equals(item.getIdPaciente(), pedido.getIdPaciente()))
				.findFirst()
				.orElse(null);
		List<DetalleOrdenResponseDto> detalles = servicioDetalleOrden.listarDetalleOrden().stream()
				.filter(detalle -> id.equals(detalle.getIdPedido()))
				.toList();
		Map<Integer, String> catalogosPorId = servicioDetalleCatalogo.listarDetalleCatalogos().stream()
				.filter(item -> item.getIdDetalleCatalogo() != null)
				.collect(Collectors.toMap(
						DetalleCatalogoResponseDto::getIdDetalleCatalogo,
						DetalleCatalogoResponseDto::getNombre,
						(actual, reemplazo) -> actual));
		byte[] pdf = servicioPdf.generarOrdenPedidoPdf(pedido, paciente, detalles, catalogosPorId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"orden-pedido-" + pedido.getIdPedido() + ".pdf\"")
				.contentType(MediaType.APPLICATION_PDF)
				.body(pdf);
	}

    @GetMapping("/nuevo")
    public String crearOrdenPedido(@RequestParam(required = false) Integer idExamen, Model model) {
	    	OrdenPedidoRequestDto ordenPedido = new OrdenPedidoRequestDto();
	    	ordenPedido.setFechaRegistro(LocalDateTime.now());
	    	ordenPedido.setFechaPedido(java.time.LocalDate.now());
	    	if (idExamen != null) {
	    		ExamenVisualResponseDto examen = servicioExamenVisual.buscarPorId(idExamen);
	    		ordenPedido.setIdExamen(idExamen);
	    		ordenPedido.setIdPaciente(examen.getIdPaciente());
	    	}
	    	model.addAttribute("ordenpedido", ordenPedido);
		cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), null, false);
            configurarFormulario(model, false);
        return "ordenespedido/crearOrdenPedido";
    }
    
    @PostMapping("/guardar")
    public String guardarOrdenPedido(@Valid @ModelAttribute OrdenPedidoRequestDto ordenPedido,
			BindingResult bindingResult,
    		RedirectAttributes redirectAttributes,
    		Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Revise los campos obligatorios del formulario");
			model.addAttribute("ordenpedido", ordenPedido);
			cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido(), false);
			configurarFormulario(model, false);
			return "ordenespedido/crearOrdenPedido";
		}
    	
    	try {
	    		if (ordenPedido.getFechaRegistro() == null) {
	    			ordenPedido.setFechaRegistro(LocalDateTime.now());
	    		}
    		servicioOrdenPedido.guardarOrdenPedido(ordenPedido);
    		redirectAttributes.addFlashAttribute("success", "Orden registrada correctamente");
    		return "redirect:/ordenespedido";
    	}catch (RuntimeException o){
    		model.addAttribute("error", o.getMessage());
	            model.addAttribute("ordenpedido", ordenPedido);
	            cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido(), false);
                configurarFormulario(model, false);
            return "ordenespedido/crearOrdenPedido";
    	}
    	
    	
    }
    
    @GetMapping("/editar/{id}")
    public String editarOrdenPedido(@PathVariable Long id, Model model) {
    	
    	OrdenPedidoResponseDto ordenActual = servicioOrdenPedido.buscarPorId(id);
		OrdenPedidoRequestDto ordenPedido = new OrdenPedidoRequestDto();
		ordenPedido.setIdPedido(ordenActual.getIdPedido());
		ordenPedido.setIdExamen(ordenActual.getIdExamen());
		ordenPedido.setIdPaciente(ordenActual.getIdPaciente());
		ordenPedido.setFechaPedido(ordenActual.getFechaPedido());
		ordenPedido.setFechaEntrega(ordenActual.getFechaEntrega());
		ordenPedido.setIdEstadoPedido(ordenActual.getIdEstadoPedido());
		ordenPedido.setFechaRegistro(ordenActual.getFechaRegistro());
    	
    	model.addAttribute("ordenpedido", ordenPedido);
		cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido(), true);
	    	configurarFormulario(model, true);
		return "ordenespedido/crearOrdenPedido";
    	
    }

	@GetMapping("/eliminar/{id}")
	public String eliminarOrdenPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		servicioOrdenPedido.eliminarOrdenPedido(id);
		redirectAttributes.addFlashAttribute("success", "Orden eliminada correctamente");
		return "redirect:/ordenespedido";
	}
    
    @PostMapping("/actualizar")
    public String actualizarOrdenPedido(@Valid @ModelAttribute OrdenPedidoRequestDto ordenPedido,
			BindingResult bindingResult,
    		RedirectAttributes redirectAttributes,
    		Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Revise los campos obligatorios del formulario");
			model.addAttribute("ordenpedido", ordenPedido);
			cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido(), true);
			configurarFormulario(model, true);
			return "ordenespedido/crearOrdenPedido";
		}
    	try {
	    		ordenPedido.setFechaRegistro(ordenPedido.getFechaRegistro() != null ? ordenPedido.getFechaRegistro() : LocalDateTime.now());
    		
	    		servicioOrdenPedido.actualizarOdenPedido(ordenPedido.getIdPedido().longValue(), ordenPedido);
	    		redirectAttributes.addFlashAttribute("success", "Datos actualizados exitosamente");
    		return "redirect:/ordenespedido";
    		
    	}catch(RuntimeException o) {
    		model.addAttribute("error", o.getMessage());
	            model.addAttribute("ordenpedido", ordenPedido);
	            cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido(), true);
                configurarFormulario(model, true);
            return "ordenespedido/crearOrdenPedido";
    	}
    }

	private void cargarCombos(Model model, Integer idPacienteSeleccionado, Integer idExamenSeleccionado,
			Integer idEstadoSeleccionado, boolean modoEdicion) {
		List<PacienteResponseDto> pacientesActivos = servicioPaciente.listarPacientes().stream()
				.filter(paciente -> Boolean.TRUE.equals(paciente.getActivo())
						|| (modoEdicion && idPacienteSeleccionado != null
								&& idPacienteSeleccionado.equals(paciente.getIdPaciente())))
				.toList();

		List<ExamenVisualResponseDto> examenesActivos = servicioExamenVisual.listarExamenesVisuales().stream()
				.filter(examen -> examen.isEstado()
						|| (idExamenSeleccionado != null && idExamenSeleccionado.equals(examen.getIdExamen())))
				.toList();

		List<DetalleCatalogoResponseDto> estadosPedido = servicioDetalleCatalogo.listarDetalleCatalogos().stream()
				.filter(detalle -> (
						detalle.isEstado()
						&& detalle.getIdentificador() != null
						&& "EST".equalsIgnoreCase(detalle.getIdentificador().trim())
					) || (idEstadoSeleccionado != null && idEstadoSeleccionado.equals(detalle.getIdDetalleCatalogo())))
				.toList();

		model.addAttribute("listapacientes", pacientesActivos);
		model.addAttribute("listaexamenesvisuales", examenesActivos);
		model.addAttribute("listaestadospedido", estadosPedido);
	}

	private void configurarFormulario(Model model, boolean modoEdicion) {
		model.addAttribute("modoEdicion", modoEdicion);
	}
    
}

