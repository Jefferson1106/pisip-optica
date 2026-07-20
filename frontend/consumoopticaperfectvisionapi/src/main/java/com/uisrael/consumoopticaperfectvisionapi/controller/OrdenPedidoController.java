package com.uisrael.consumoopticaperfectvisionapi.controller;

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
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;

@Controller
@RequestMapping("/ordenespedido")
public class OrdenPedidoController {
	
	@Autowired
	private IOrdenPedido servicioOrdenPedido;
	
	@GetMapping
	public String leerPagina(Model model) {
		List<OrdenPedidoResponseDto> resultadoBD = servicioOrdenPedido.listarOrdenPedido();
		model.addAttribute("listaordenpedidos", resultadoBD);
		return "ordenespedido/listarordenpedido";
	}

    @GetMapping("/nuevo")
    public String crearOrdenPedido(Model model) {
    	model.addAttribute("ordenpedido", new OrdenPedidoRequestDto());
        return "ordenespedido/crearOrdenPedido";
    }
    
    @PostMapping("/guardar")
    public String guardarOrdenPedido(@ModelAttribute OrdenPedidoRequestDto ordenPedido,
    		RedirectAttributes redirectAttributes,
    		Model model) {
    	
    	try {
    		servicioOrdenPedido.guardarOrdenPedido(ordenPedido);
    		redirectAttributes.addFlashAttribute("success", "Orden registrada correctamente");
    		return "redirect:/ordenespedido";
    	}catch (RuntimeException o){
    		model.addAttribute("error", o.getMessage());
            model.addAttribute("paciente", ordenPedido);
            return "ordenespedido/crearOrdenPedido";
    	}
    	
    	
    }
    
    @GetMapping("/editar/{id}")
    public String editarOrdenPedido(@PathVariable Long id, Model model) {
    	
    	OrdenPedidoResponseDto ordenPedido = servicioOrdenPedido.buscarPorId(id);
    	
    	model.addAttribute("ordenpedido", ordenPedido);
    	
    	return "ordenespedido/actualizarOrdenPedido";
    	
    }
    
    @PostMapping("/actualizar")
    public String actualizarOrdenPedido(@ModelAttribute OrdenPedidoRequestDto ordenPedido,
    		RedirectAttributes redirectAttributes,
    		Model model) {
    	try {
    		
    		servicioOrdenPedido.actualizarOdenPedido(ordenPedido);
    		redirectAttributes.addAttribute("success", "Datos actulizados exitosamente");
    		return "redirect:/ordenespedido";
    		
    	}catch(RuntimeException o) {
    		model.addAttribute("error", o.getMessage());
            model.addAttribute("paciente", ordenPedido);
            return "ordenespedido/actualizarOrdenPedido";
    	}
    }
    
}

