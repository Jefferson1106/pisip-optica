package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.OrdenPedidoRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;

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
	
	@GetMapping
	public String leerPagina(Model model) {
		List<OrdenPedidoResponseDto> resultadoBD = servicioOrdenPedido.listarOrdenPedido();
		model.addAttribute("listaordenpedidos", resultadoBD);
		return "ordenespedido/listarordenpedido";
	}

    @GetMapping("/nuevo")
    public String crearOrdenPedido(Model model) {
	    	OrdenPedidoRequestDto ordenPedido = new OrdenPedidoRequestDto();
	    	ordenPedido.setFechaRegistro(LocalDateTime.now());
	    	model.addAttribute("ordenpedido", ordenPedido);
	    	cargarCombos(model, null, null, null);
        return "ordenespedido/crearOrdenPedido";
    }
    
    @PostMapping("/guardar")
    public String guardarOrdenPedido(@ModelAttribute OrdenPedidoRequestDto ordenPedido,
    		RedirectAttributes redirectAttributes,
    		Model model) {
    	
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
	            cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido());
            return "ordenespedido/crearOrdenPedido";
    	}
    	
    	
    }
    
    @GetMapping("/editar/{id}")
    public String editarOrdenPedido(@PathVariable Long id, Model model) {
    	
    	OrdenPedidoResponseDto ordenPedido = servicioOrdenPedido.buscarPorId(id);
    	
    	model.addAttribute("ordenpedido", ordenPedido);
	    	cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido());
    	
    	return "ordenespedido/actualizarOrdenPedido";
    	
    }

	@GetMapping("/eliminar/{id}")
	public String eliminarOrdenPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		servicioOrdenPedido.eliminarOrdenPedido(id);
		redirectAttributes.addFlashAttribute("success", "Orden eliminada correctamente");
		return "redirect:/ordenespedido";
	}
    
    @PostMapping("/actualizar")
    public String actualizarOrdenPedido(@ModelAttribute OrdenPedidoResponseDto ordenPedido,
    		RedirectAttributes redirectAttributes,
    		Model model) {
    	try {
	    		OrdenPedidoRequestDto requestDto = new OrdenPedidoRequestDto();
	    		requestDto.setIdExamen(ordenPedido.getIdExamen());
	    		requestDto.setIdPaciente(ordenPedido.getIdPaciente());
	    		requestDto.setFechaPedido(ordenPedido.getFechaPedido());
	    		requestDto.setFechaEntrega(ordenPedido.getFechaEntrega());
	    		requestDto.setIdEstadoPedido(ordenPedido.getIdEstadoPedido());
	    		requestDto.setFechaRegistro(ordenPedido.getFechaRegistro() != null ? ordenPedido.getFechaRegistro() : LocalDateTime.now());
    		
	    		servicioOrdenPedido.actualizarOdenPedido(ordenPedido.getIdPedido().longValue(), requestDto);
	    		redirectAttributes.addFlashAttribute("success", "Datos actualizados exitosamente");
    		return "redirect:/ordenespedido";
    		
    	}catch(RuntimeException o) {
    		model.addAttribute("error", o.getMessage());
	            model.addAttribute("ordenpedido", ordenPedido);
	            cargarCombos(model, ordenPedido.getIdPaciente(), ordenPedido.getIdExamen(), ordenPedido.getIdEstadoPedido());
            return "ordenespedido/actualizarOrdenPedido";
    	}
    }

	private void cargarCombos(Model model, Integer idPacienteSeleccionado, Integer idExamenSeleccionado,
			Integer idEstadoSeleccionado) {
		List<PacienteResponseDto> pacientesActivos = servicioPaciente.listarPacientes().stream()
				.filter(paciente -> Boolean.TRUE.equals(paciente.getActivo())
						|| (idPacienteSeleccionado != null && idPacienteSeleccionado.equals(paciente.getIdPaciente())))
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
    
}

